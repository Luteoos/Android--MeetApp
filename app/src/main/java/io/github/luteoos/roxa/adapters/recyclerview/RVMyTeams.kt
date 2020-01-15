package io.github.luteoos.roxa.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.luteoos.roxa.R
import kotlinx.android.synthetic.main.rv_my_teams.view.*

class RVMyTeams(private val ctx: Context, private val data: MutableList<String>) : RecyclerView.Adapter<RVMyTeams.RVMyTeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVMyTeamsViewHolder {
        return RVMyTeamsViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.rv_my_teams, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RVMyTeamsViewHolder, position: Int) {
        holder.tvName.text = data[position]
    }

    class RVMyTeamsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvName = view.tvName
    }
}
