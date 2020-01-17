package io.github.luteoos.roxa.adapters.recyclerview

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageButton
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.bumptech.glide.load.resource.bitmap.Rotate
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.adapters.listAdapter.ListTeamMembersAdapter
import io.github.luteoos.roxa.utils.Parameters
import io.github.luteoos.roxa.utils.setListViewHeightBasedOnItems
import kotlinx.android.synthetic.main.rv_my_teams.view.*

class RVMyTeams(private val ctx: Context, private val data: MutableList<String>, private val message: (String, String) -> Unit)
    : RecyclerView.Adapter<RVMyTeams.RVMyTeamsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVMyTeamsViewHolder {
        return RVMyTeamsViewHolder(
            LayoutInflater.from(ctx).inflate(R.layout.rv_my_teams, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: RVMyTeamsViewHolder, position: Int) {
        holder.tvTeamName.text = data[position]
        holder.btnUnfold.setOnClickListener {
            unfoldDetails(holder)
        }
        holder.membersList?.apply {
            adapter = ListTeamMembersAdapter(ctx, listOf("qqqa", "twojaStara", "hehehehe","qqqa", "twojaStara", "hehehehe"
                ,"qqqa", "twojaStara", "hehehehe","qqqa", "twojaStara", "hehehehe","qqqa", "twojaStara", "hehehehe"), message)
            setListViewHeightBasedOnItems()
        }
        holder.btnAddEvent.setOnClickListener {
            message(data[position], Parameters.CREATE_EVENT)
        }
        holder.btnInvite.setOnClickListener {
            message(data[position], Parameters.SHOW_TEAM_INVITATION)
        }
        holder.btnLeave.setOnClickListener {
            message(data[position], Parameters.LEAVE_TEAM)
        }
    }

    private fun unfoldDetails(holder: RVMyTeamsViewHolder){
        if(holder.detailsCard.visibility == View.GONE){
            TransitionManager.beginDelayedTransition(holder.parent, Fade(Fade.MODE_IN)
                .setDuration(300)
                .addTarget(holder.detailsCard) )
            holder.detailsCard.visibility = View.VISIBLE
        }
        else{
            TransitionManager.beginDelayedTransition(holder.parent, Fade(Fade.MODE_OUT)
                .setDuration(300)
                .addTarget(holder.detailsCard) )
            holder.detailsCard.visibility = View.GONE
        }
        holder.btnUnfold.let {
            it.animate().rotation(it.rotation + 180f).setInterpolator(AccelerateDecelerateInterpolator())
        }
    }

    class RVMyTeamsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvTeamName = view.tvTeamName
        val tvTeamDescription = view.tvTeamDescription
        val detailsCard = view.cardMembers
        val membersList = view.listMembers
        val btnUnfold = view.btnUnfold
        val btnInvite = view.btnInvitation
        val btnLeave = view.btnLeaveTeam
        val btnAddEvent = view.btnAddEvent
        val parent = view.myTeamsMainCard
    }
}
