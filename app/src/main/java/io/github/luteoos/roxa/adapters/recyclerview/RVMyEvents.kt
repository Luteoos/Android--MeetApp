package io.github.luteoos.roxa.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.utils.Parameters
import kotlinx.android.synthetic.main.rv_my_events.view.*

class RVMyEvents (private val ctx: Context, private val data: MutableList<String>, private val message: (String, String) -> Unit)
    : RecyclerView.Adapter<RVMyEvents.RVMyEventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVMyEventsViewHolder {
        return RVMyEventsViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.rv_my_events, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RVMyEventsViewHolder, position: Int) {
        data[position]?.let { data ->
            holder.rvDetailed.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                isNestedScrollingEnabled = false
                adapter = RVMyEventsDetailed(context, mutableListOf("test1", "twojastarasrarararar","i dsadsasd i czesc","test1", "twojastarasrarararar","i dsadsasd i czesc","test1", "twojastarasrarararar","i dsadsasd i czesc"),
                    message)
            }
            holder.ivAccept.setImageDrawable(ctx.getDrawable(R.drawable.ic_done_24px))
            holder.ivDecline.setImageDrawable(ctx.getDrawable(R.drawable.ic_clear_24px))
//            when(true) {//accepted invitation
//                true -> holder.ivAccept.setImageDrawable(ctx.getDrawable(R.drawable.ic_accept))
//                false -> holder.ivDecline.setImageDrawable(ctx.getDrawable(R.drawable.ic_cancel_24px))
//            }
            holder.ivAccept.setOnClickListener {
                message(data, Parameters.EVENT_ACCEPT)
                holder.ivAccept.setImageDrawable(ctx.getDrawable(R.drawable.ic_accept))//todo remove
            }
            holder.ivDecline.setOnClickListener {
                message(data, Parameters.EVENT_DECLINE)
                holder.ivDecline.setImageDrawable(ctx.getDrawable(R.drawable.ic_cancel_24px))//todo remove
            }
        }
    }

    class RVMyEventsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val rvDetailed = view.rvEventsDetailed
        val ivAccept = view.ivAccept
        val ivDecline = view.ivDecline
    }
}