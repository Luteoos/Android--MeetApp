package io.github.luteoos.roxa.adapters.listAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.amulyakhare.textdrawable.TextDrawable
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.adapters.recyclerview.RVMyDays
import io.github.luteoos.roxa.adapters.recyclerview.RVMyDaysDetailed
import io.github.luteoos.roxa.model.User
import kotlinx.android.synthetic.main.rv_adapter_team_members.view.*

class ListTeamMembersAdapter(private val ctx: Context, private val data: MutableList<User>, private val message: (String, String) -> Unit) :
    ArrayAdapter<User>(ctx, R.layout.rv_adapter_team_members, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        if(view == null){
            view = LayoutInflater.from(ctx).inflate(R.layout.rv_adapter_team_members, null)
        }

        data[position]?.let {data ->
            view?.let {v ->
                v.ivMemberName.setImageDrawable(getUsernamePlaceholder(data.name ?: ""))
                v.rvMembersDays.apply {
                    layoutManager = LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)
                    isNestedScrollingEnabled = false
                    adapter = RVMyDaysDetailed(ctx, data.freeTime ?: mutableListOf() ,{_,_ ->}, false)
                }
            }
        }

        return view!!
    }

    private fun getUsernamePlaceholder(username: String) = TextDrawable.builder()
        .beginConfig()
        .bold()
        .fontSize(30)
        .endConfig()
        .buildRound(username, ContextCompat.getColor(ctx, R.color.colorBackground))
}