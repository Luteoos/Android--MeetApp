package io.github.luteoos.roxa.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.utils.Parameters
import kotlinx.android.synthetic.main.rv_my_days_detailed.view.*

class RVMyDaysDetailed(private val ctx: Context, private val data: MutableList<String>,
                       private val message: (String, String) -> Unit,  private val deletable : Boolean = true ) :
    RecyclerView.Adapter<RVMyDaysDetailed.RVMyDaysDetailedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVMyDaysDetailedViewHolder {
        return RVMyDaysDetailedViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.rv_my_days_detailed, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RVMyDaysDetailedViewHolder, position: Int) {
        holder.start.text = data[position]
        holder.end.text = data[position]
        if(!deletable)
            holder.btnDelete.visibility = View.GONE
        else
            holder.btnDelete.setOnClickListener {
                message("qqqe uid", Parameters.DELETE_FREE_TIME)
            }
    }

    class RVMyDaysDetailedViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val start = view.tvDateFrom
        val end = view.tvDateTo
        val btnDelete = view.btnDelete
//        val rvDaysDetailed = view.rvDaysDetailed
    }
}