package io.github.luteoos.roxa.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import io.github.luteoos.roxa.baseAbstract.BaseFragment
import io.github.luteoos.roxa.viewmodel.MainScreenViewModel
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.adapters.recyclerview.RVMyTeams
import io.github.luteoos.roxa.utils.Parameters
import io.github.luteoos.roxa.view.activity.CreateEventDialogActivity
import io.github.luteoos.roxa.view.activity.FreeTimeDialogActivity
import kotlinx.android.synthetic.main.fragment_my_teams.*

class MyTeamsFragment : BaseFragment<MainScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.fragment_my_teams

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(activity!!)
        setTestData()
    }

    private fun setTestData(){
        rvMyTeams.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RVMyTeams(context, mutableListOf("qqqa 1", "qqqa2", "qqqa3","qqqqq")){uuid, parameter ->
                handleRVButtons(uuid, parameter)
            }
        }
    }

    override fun refresh() {
    }

    private fun handleRVButtons(uuid: String, parameter: String){
        when(parameter){
            Parameters.LEAVE_TEAM -> {}
            Parameters.SHOW_TEAM_INVITATION -> {}
            Parameters.CREATE_EVENT -> createEvent(uuid)
        }
    }

    private fun createEvent(teamUUID: String){
        activity?.let {
            val intent = Intent(it, CreateEventDialogActivity::class.java)
            it.startActivityForResult(intent, Parameters.OPEN_DIALOG_ACTIVITY)
        }
    }
}