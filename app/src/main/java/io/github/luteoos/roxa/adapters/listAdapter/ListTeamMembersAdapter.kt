package io.github.luteoos.roxa.adapters.listAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import io.github.luteoos.roxa.R
import kotlinx.android.synthetic.main.rv_my_teams.view.*

class ListTeamMembersAdapter(private val ctx: Context, private val data: List<String>) :
    ArrayAdapter<String>(ctx, R.layout.rv_my_teams, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var view = convertView
        if(view == null){
            view = LayoutInflater.from(ctx).inflate(R.layout.rv_my_teams, null)
        }

        data[position]?.let {data ->
            view?.let {v ->
                v.tvTeamName.text = "qqqA DZIALA KEK"
                v.tvTeamDescription.text = data
            }
        }

        return view!!
    }
}