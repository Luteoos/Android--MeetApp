package io.github.luteoos.roxa.adapters.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.adapters.listAdapter.ListTeamMembersAdapter
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
        holder.membersList?.apply {
            adapter = ListTeamMembersAdapter(ctx, listOf("qqqa", "twojaStara", "hehehehe"))
            setListViewHeightBasedOnItems()
        }
    }

    class RVMyTeamsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvTeamName = view.tvTeamName
        val tvTeamDescription = view.tvTeamDescription
        val detailsCard = view.cardMembers
        val membersList = view.listMembers
    }
}
