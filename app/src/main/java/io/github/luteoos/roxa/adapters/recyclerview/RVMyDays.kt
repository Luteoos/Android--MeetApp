package io.github.luteoos.roxa.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.utils.Parameters
import kotlinx.android.synthetic.main.rv_my_days.view.*

class RVMyDays(private val ctx: Context, private val data: MutableList<String>,
               private val message: (String, String) -> Unit )
    : RecyclerView.Adapter<RVMyDays.RVMyDaysViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVMyDaysViewHolder {
        return RVMyDaysViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.rv_my_days, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RVMyDaysViewHolder, position: Int) {
        holder.tvTeamName.text = data[position]
        holder.btnAddDay.setOnClickListener {
            message(data[position], Parameters.ADD_FREE_TIME)
        }
        holder.rvDaysDetailed.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = RVMyDaysDetailed(context, mutableListOf("Wtorek 12.12.1939 17:00","Wtorek 12.12.1939 17:00","  Wtorek 12.12.1939 17:00","Wtorek 12.12.1939 17:00","Wtorek 12.12.1939 17:00"), message)
            isNestedScrollingEnabled = false
        }
    }

    class RVMyDaysViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvTeamName = view.tvTeamName
        val rvDaysDetailed = view.rvDaysDetailed
        val btnAddDay = view.btnAdd
    }
}