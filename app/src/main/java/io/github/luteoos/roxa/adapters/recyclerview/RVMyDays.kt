package io.github.luteoos.roxa.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.network.response.MyFreeTimeResponse
import io.github.luteoos.roxa.utils.Parameters
import kotlinx.android.synthetic.main.rv_my_days.view.*

class RVMyDays(private val ctx: Context, private val data: MutableList<MyFreeTimeResponse>,
               private val message: (String, String) -> Unit )
    : RecyclerView.Adapter<RVMyDays.RVMyDaysViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVMyDaysViewHolder {
        return RVMyDaysViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.rv_my_days, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RVMyDaysViewHolder, position: Int) {
        data[position]?.let{ data ->
            holder.tvTeamName.text = data.groupName
            holder.btnAddDay.setOnClickListener {
                message(data.groupId!!, Parameters.ADD_FREE_TIME)
            }
//            data.freeTime?.first()?.freeTime?.sortBy { it.startTime }
            holder.rvDaysDetailed.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = RVMyDaysDetailed(context, data.freeTime?.first()?.freeTime ?: mutableListOf(), message)
                isNestedScrollingEnabled = false
                this.adapter?.notifyDataSetChanged()
            }
        }
    }

    class RVMyDaysViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvTeamName = view.tvTeamName
        val rvDaysDetailed = view.rvDaysDetailed
        val btnAddDay = view.btnAdd
    }
}