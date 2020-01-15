package io.github.luteoos.roxa.view.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import es.dmoral.toasty.Toasty
import io.github.luteoos.roxa.baseAbstract.BaseFragment
import io.github.luteoos.roxa.viewmodel.MainScreenViewModel
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.adapters.recyclerview.RVMyTeams
import kotlinx.android.synthetic.main.fragment_my_teams.*

class MyTeamsFragment : BaseFragment<MainScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.fragment_my_teams

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTestData()
    }

    private fun setTestData(){
        rvMyTeams.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RVMyTeams(context, mutableListOf("qqqa 1", "qqqa2", "qqqa3","qqqqq")){uuid, parameter ->
                Toasty.success(context, uuid + parameter).show()
            }
        }
    }

    override fun refresh() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}