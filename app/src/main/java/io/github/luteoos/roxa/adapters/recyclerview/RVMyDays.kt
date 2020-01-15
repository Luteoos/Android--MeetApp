package io.github.luteoos.roxa.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.luteoos.roxa.R
import kotlinx.android.synthetic.main.rv_my_days.view.*

class RVMyDays(private val ctx: Context, private val data: MutableList<String>) : RecyclerView.Adapter<RVMyDays.RVMyDaysViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVMyDaysViewHolder {
        return RVMyDaysViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.rv_my_days, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RVMyDaysViewHolder, position: Int) {
        holder.tvName.text = data[position]
    }

    class RVMyDaysViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvName = view.textView2
    }
}