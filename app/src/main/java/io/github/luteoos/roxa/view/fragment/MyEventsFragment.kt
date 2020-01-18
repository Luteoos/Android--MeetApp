package io.github.luteoos.roxa.view.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import io.github.luteoos.roxa.baseAbstract.BaseFragment
import io.github.luteoos.roxa.viewmodel.MainScreenViewModel
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.adapters.recyclerview.RVMyDays
import io.github.luteoos.roxa.adapters.recyclerview.RVMyEvents
import io.github.luteoos.roxa.model.Event
import io.github.luteoos.roxa.network.response.MyFreeTimeResponse
import io.github.luteoos.roxa.utils.Parameters
import kotlinx.android.synthetic.main.fragment_my_events.*
import kotlinx.android.synthetic.main.fragment_my_teams.*

class MyEventsFragment : BaseFragment<MainScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.fragment_my_events

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(activity!!)
        observeData()
        refresh()
    }

    override fun refresh() {
        viewModel.getEvents()
    }

    private fun observeData(){
        viewModel.getEventsLiveData().observe(this, Observer { list ->
            setRVEvents(list)
        })
    }

    private fun setRVEvents(list: MutableList<Event>){
        rvEvents.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RVMyEvents(context, list){ uuid, parameter ->
                handleRVButtons(uuid, parameter)
            }
            this.adapter?.notifyDataSetChanged()
        }
    }

    private fun handleRVButtons(uuid: String, parameter: String){
        when(parameter){
            Parameters.EVENT_ACCEPT -> viewModel.respondToEvent(uuid, true)
            Parameters.EVENT_DECLINE -> viewModel.respondToEvent(uuid, false)
        }
    }
}