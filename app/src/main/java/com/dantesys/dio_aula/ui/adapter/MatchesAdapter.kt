package com.dantesys.dio_aula.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dantesys.dio_aula.databinding.ActivityMainBinding
import com.dantesys.dio_aula.databinding.PartidaItemBinding
import com.dantesys.dio_aula.domain.Match
import com.dantesys.dio_aula.ui.DetailActivity

class MatchesAdapter(private val matches:List<Match>) :
    RecyclerView.Adapter<MatchesAdapter.ViewHolder>() {

    class ViewHolder(var matchitembiding:PartidaItemBinding) : RecyclerView.ViewHolder(matchitembiding.root) {
    }
    fun getList(): List<Match> {
        return matches
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutContext = LayoutInflater.from(parent.context)
        val matchitembiding = PartidaItemBinding.inflate(layoutContext,parent,false)

        return ViewHolder(matchitembiding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val match = matches[position]
        val context = holder.itemView.context
        Glide.with(context).load(match.homeTeam.img).circleCrop().into(holder.matchitembiding.timeCasa)
        holder.matchitembiding.timeCasaNome.text = match.homeTeam.nome
        if(match.homeTeam.score!=null){
            holder.matchitembiding.placarCasa.text = match.homeTeam.score.toString()
        }
        Glide.with(context).load(match.awayTeam.img).circleCrop().into(holder.matchitembiding.timeFora)
        holder.matchitembiding.timeForaNome.text = match.awayTeam.nome
        if(match.awayTeam.score!=null){
            holder.matchitembiding.placarFora.text = match.awayTeam.score.toString()
        }
        holder.itemView.setOnClickListener {
            var intent = Intent(context,DetailActivity::class.java)
            intent.putExtra(DetailActivity.Extras.MATCH,match)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {return matches.size}

}