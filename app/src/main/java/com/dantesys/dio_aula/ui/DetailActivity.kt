package com.dantesys.dio_aula.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dantesys.dio_aula.databinding.ActivityDetailBinding
import com.dantesys.dio_aula.domain.Match

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    object Extras{
        const val MATCH = "EXTRA_MATCH"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let {
            Glide.with(this).load(it.place.img).into(binding.ivPlace)
            supportActionBar?.title = it.place.name
            binding.tvDescription.text = it.description
            binding.tvAwayTeamName.text = it.awayTeam.nome
            if(it.awayTeam.score!=null){
                binding.tvAwayTeamScore.text = it.awayTeam.score.toString()
            }
            Glide.with(this).load(it.awayTeam.img).into(binding.ivAwayTeam)
            binding.rbAwayTeamStars.rating = it.awayTeam.force.toFloat()
            binding.tvHomeTeamName.text = it.homeTeam.nome
            if(it.homeTeam.score!=null){
                binding.tvHomeTeamScore.text = it.homeTeam.score.toString()
            }
            Glide.with(this).load(it.homeTeam.img).into(binding.ivHomeTeam)
            binding.rbHomeTeamStars.rating = it.homeTeam.force.toFloat()
        }
    }
}