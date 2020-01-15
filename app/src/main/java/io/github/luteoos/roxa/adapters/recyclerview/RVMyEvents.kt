package io.github.luteoos.roxa.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.luteoos.roxa.R

class RVMyEvents (private val ctx: Context, private val data: MutableList<String>) : RecyclerView.Adapter<RVMyEvents.RVMyEvensViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVMyEvensViewHolder {
        return RVMyEvensViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.rv_my_events, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RVMyEvensViewHolder, position: Int) {
//        holder.tvName.text = data[position]
    }

    class RVMyEvensViewHolder(view: View) : RecyclerView.ViewHolder(view){
//        val tvName = view.tvName
    }
}