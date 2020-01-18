package io.github.luteoos.roxa.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import io.github.luteoos.roxa.baseAbstract.BaseFragment
import io.github.luteoos.roxa.viewmodel.MainScreenViewModel
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.adapters.recyclerview.RVMyDays
import io.github.luteoos.roxa.adapters.recyclerview.RVMyTeams
import io.github.luteoos.roxa.model.Group
import io.github.luteoos.roxa.network.response.MyFreeTimeResponse
import io.github.luteoos.roxa.utils.Parameters
import io.github.luteoos.roxa.view.activity.FreeTimeDialogActivity
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.fragment_my_days.*
import kotlinx.android.synthetic.main.fragment_my_teams.*

class MyDaysFragment : BaseFragment<MainScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.fragment_my_days

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(activity!!)
        observeData()
        refresh()
    }

    override fun refresh() {
        viewModel.getFreeTime()
    }

    private fun observeData(){
        viewModel.getFreeTimeLiveData().observe(this, Observer { list ->
            setRVDays(list)
        })
    }

    private fun setRVDays(list: MutableList<MyFreeTimeResponse>){
        rvDays.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RVMyDays(context, list){ uuid, parameter ->
                handleRVButtons(uuid, parameter)
            }
        }
    }

    private fun handleRVButtons(uuid: String, parameter: String){
        when(parameter){
            Parameters.DELETE_FREE_TIME -> {viewModel.deleteFreeTime(uuid)}
            Parameters.ADD_FREE_TIME -> createFreeTime(uuid)
        }
    }

    private fun createFreeTime(teamUUID: String ){
        activity?.let {
            val intent = Intent(it, FreeTimeDialogActivity::class.java)
            intent.putExtra(Parameters.TEAM_ID, teamUUID)
            it.startActivityForResult(intent, Parameters.OPEN_DIALOG_ACTIVITY)
        }
    }
}