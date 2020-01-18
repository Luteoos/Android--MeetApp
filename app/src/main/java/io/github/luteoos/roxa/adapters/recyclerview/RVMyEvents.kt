package io.github.luteoos.roxa.adapters.recyclerview

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.model.Event
import io.github.luteoos.roxa.utils.Parameters
import io.github.luteoos.roxa.utils.Session
import io.github.luteoos.roxa.utils.getFormattedDate
import io.github.luteoos.roxa.utils.toCalendarInstance
import kotlinx.android.synthetic.main.rv_my_events.view.*
import java.lang.Exception
import java.util.*

class RVMyEvents (private val ctx: Context, private val data: MutableList<Event>, private val message: (String, String) -> Unit)
    : RecyclerView.Adapter<RVMyEvents.RVMyEventsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVMyEventsViewHolder {
        return RVMyEventsViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.rv_my_events, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RVMyEventsViewHolder, position: Int) {
        data[position]?.let { data ->
            val isUserGoing = data.isGoing?.first { it.userId == Session.userUUIDString }?.isGoing
            holder.rvDetailed.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                isNestedScrollingEnabled = false
                adapter = RVMyEventsDetailed(context, data.isGoing ?: mutableListOf(),
                    message)
            }
            holder.tvTeamName.text = data.groupName ?: ""
            holder.tvEventName.text = data.name ?: ""
            holder.tvEventDesc.text = data.description ?: ""
            holder.tvEventLocal.text = data.description ?: ""
            holder.start.text = ctx.getString(R.string.date_event_from, data.startTime!!.getFormattedDate())
            holder.end.text = ctx.getString(R.string.date_event_to, data.endTime!!.getFormattedDate())

            holder.btnAddToCalendar.setOnClickListener {
                try{
                    startDeviceCalendarIntent(data.startTime!!.toCalendarInstance(),
                        data.endTime!!.toCalendarInstance(),
                        data.name ?: "",
                        data.description ?: "")
                }catch(e: Exception){}
            }

            resetDrawables(holder)
            when(isUserGoing) {
                true -> holder.ivAccept.setImageDrawable(ctx.getDrawable(R.drawable.ic_accept))
                false -> holder.ivDecline.setImageDrawable(ctx.getDrawable(R.drawable.ic_cancel_24px))
            }
            if(isUserGoing != true)
            holder.ivAccept.setOnClickListener {
                message(data.id!!, Parameters.EVENT_ACCEPT)
                resetDrawables(holder)
                holder.ivAccept.setImageDrawable(ctx.getDrawable(R.drawable.ic_accept))
            }
            if (isUserGoing != false)
            holder.ivDecline.setOnClickListener {
                message(data.id!!, Parameters.EVENT_DECLINE)
                resetDrawables(holder)
                holder.ivDecline.setImageDrawable(ctx.getDrawable(R.drawable.ic_cancel_24px))
            }
        }
    }

    private fun startDeviceCalendarIntent(start: Calendar, end: Calendar, name: String, description: String){
        val intent = Intent(Intent.ACTION_EDIT)
        intent.data = CalendarContract.Events.CONTENT_URI
        intent.type = "vnd.android.cursor.item/event" //todo kick if wont work?
        intent.putExtra(CalendarContract.Events.TITLE, name)
        intent.putExtra(CalendarContract.Events.ALL_DAY, false)
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description)
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, start.timeInMillis)
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end.timeInMillis)
        ctx.startActivity(intent)
    }

    private fun resetDrawables(holder: RVMyEventsViewHolder){
        holder.ivAccept.setImageDrawable(ctx.getDrawable(R.drawable.ic_done_24px))
        holder.ivDecline.setImageDrawable(ctx.getDrawable(R.drawable.ic_clear_24px))
    }

    class RVMyEventsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val rvDetailed = view.rvEventsDetailed
        val ivAccept = view.ivAccept
        val ivDecline = view.ivDecline
        val tvTeamName = view.tvEventTeamName
        val tvEventName = view.tvEventName
        val tvEventDesc = view.tvEventDescription
        val tvEventLocal = view.tvEventLocalization
        val end = view.tvEventTo
        val start = view.tvEventFrom
        val btnAddToCalendar = view.tvAddToDevice
    }
}