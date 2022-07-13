package com.dantesys.dio_aula.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dantesys.dio_aula.R
import com.dantesys.dio_aula.data.MatchesAPI
import com.dantesys.dio_aula.databinding.ActivityMainBinding
import com.dantesys.dio_aula.domain.Match
import com.dantesys.dio_aula.ui.adapter.MatchesAdapter
import com.google.android.material.snackbar.Snackbar
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var matchesAPI: MatchesAPI
    private lateinit var matchesAdapter:MatchesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupHttpClient()
        setupMatchesList()
        setupMatchesRefresh()
        setupFloatingActionButton()
    }

    private fun setupHttpClient() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dantesys.github.io/dio-partidas-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        matchesAPI = retrofit.create(MatchesAPI::class.java)
    }

    private fun setupFloatingActionButton() {
        binding.fabSimulacao.setOnClickListener {
            it.animate().rotationBy(360F).setDuration(500L).setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    var random = Random
                    for (i in 0..matchesAdapter.itemCount-1){
                        var match = matchesAdapter.getList()[i]
                        match.homeTeam.score = random.nextInt(match.homeTeam.force+1)
                        match.awayTeam.score = random.nextInt(match.awayTeam.force+1)
                        matchesAdapter.notifyItemChanged(i)
                    }
                }
            })
        }
    }

    private fun setupMatchesRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            getMatchesList()
        }
    }

    private fun setupMatchesList() {
        binding.rvList.setHasFixedSize(true)
        binding.rvList.layoutManager = LinearLayoutManager(binding.root.context)
        getMatchesList()
    }
    private fun getMatchesList(){
        binding.swipeRefreshLayout.isRefreshing = true
        matchesAPI.matches.enqueue(object : Callback<List<Match>> {
            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                showErrorMessage(t.localizedMessage)
            }
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if(response.isSuccessful){
                    val match = response.body()
                    if (match != null) {
                        matchesAdapter = MatchesAdapter(match)
                        binding.rvList.adapter = matchesAdapter
                    }else{
                        Log.i("Simulador","Tudo OK num chegou nada :(")
                    }
                }else{
                    showErrorMessage("Vazio")
                }
            }
        })
        binding.swipeRefreshLayout.isRefreshing = false
    }
    private fun showErrorMessage(msg:String) {
        Snackbar.make(binding.fabSimulacao, msg,Snackbar.LENGTH_LONG).show()
    }
}