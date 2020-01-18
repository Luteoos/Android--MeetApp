package io.github.luteoos.roxa.adapters.recyclerview

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.model.IsGoingWrapper
import kotlinx.android.synthetic.main.rv_my_events_detailed.view.*

class RVMyEventsDetailed(private val ctx: Context, private val data: MutableList<IsGoingWrapper>, private val message: (String, String) -> Unit)
    : RecyclerView.Adapter<RVMyEventsDetailed.RVMyEventsDetailedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVMyEventsDetailedViewHolder {
        return RVMyEventsDetailedViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.rv_my_events_detailed, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RVMyEventsDetailedViewHolder, position: Int) {
        data[position]?.let { data ->
            getUsernamePlaceholder(data.username ?: " ").let {
                holder.ivMemberName.minimumWidth = it.intrinsicWidth
                holder.ivMemberName.setImageDrawable(it)
            }
            holder.ivIcon.setImageDrawable(getIconForMember(data.isGoing))
        }
    }

    private fun getUsernamePlaceholder(username: String) = TextDrawable.builder()
        .beginConfig()
        .width(username.length*15)
        .fontSize(24)
        .endConfig()
        .buildRoundRect(username, ContextCompat.getColor(ctx, R.color.colorBackground),10)

    private fun getIconForMember(isGoing: Boolean?) =
        ctx.getDrawable(when(isGoing){
            true -> R.drawable.ic_accept
            false -> R.drawable.ic_cancel_24px
            null -> R.drawable.ic_questionmark
        })

    class RVMyEventsDetailedViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val ivMemberName = view.ivMemberName
        val ivIcon = view.ivAvailability
    }
}