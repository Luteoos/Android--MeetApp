package io.github.luteoos.roxa.view.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import io.github.luteoos.roxa.baseAbstract.BaseFragment
import io.github.luteoos.roxa.viewmodel.MainScreenViewModel
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.adapters.recyclerview.RVMyEvents
import io.github.luteoos.roxa.utils.Parameters
import kotlinx.android.synthetic.main.fragment_my_events.*

class MyEventsFragment : BaseFragment<MainScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.fragment_my_events

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(activity!!)
        setRV()
    }

    private fun setRV(){
        rvEvents.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RVMyEvents(context, mutableListOf("testy","qqq","dsadsasddsadsasd","testy","qqq","dsadsasddsadsasd","testy","qqq","dsadsasddsadsasd"))
            {uuid, parameters ->
                handleRVButtons(uuid, parameters)
            }
        }
    }

    override fun refresh() {
    }

    private fun handleRVButtons(uuid: String, parameter: String){
        when(parameter){
            Parameters.EVENT_ACCEPT ->{}
            Parameters.EVENT_DECLINE -> {}
        }
    }
}