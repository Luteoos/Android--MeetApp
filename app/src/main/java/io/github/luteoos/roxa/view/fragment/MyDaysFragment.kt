package io.github.luteoos.roxa.view.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.luteoos.roxa.baseAbstract.BaseFragment
import io.github.luteoos.roxa.viewmodel.MainScreenViewModel
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.adapters.recyclerview.RVMyDays
import io.github.luteoos.roxa.adapters.recyclerview.RVMyTeams
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.fragment_my_days.*

class MyDaysFragment : BaseFragment<MainScreenViewModel>() {

    override fun getLayoutID(): Int = R.layout.fragment_my_days

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTestData()
    }

    fun setTestData(){
        val testList = mutableListOf<String>()
        for(int in 0..80){
            testList.add("This item is item number ${int.toString()}")
        }

        rvDays.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = RVMyDays(
                context,
                testList
            )
        }
    }

    override fun refresh() {
    }
}