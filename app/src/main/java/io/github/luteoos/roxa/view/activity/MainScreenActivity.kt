package io.github.luteoos.roxa.view.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.amulyakhare.textdrawable.TextDrawable
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.adapters.recyclerview.RVMyTeams
import io.github.luteoos.roxa.baseAbstract.*
import io.github.luteoos.roxa.utils.Session
import io.github.luteoos.roxa.view.fragment.MyDaysFragment
import io.github.luteoos.roxa.view.fragment.MyEventsFragment
import io.github.luteoos.roxa.view.fragment.MyTeamsFragment
import io.github.luteoos.roxa.viewmodel.MainScreenViewModel
import kotlinx.android.synthetic.main.activity_main_screen.*
import java.lang.Exception

class MainScreenActivity : BaseActivity<MainScreenViewModel>() {

    private lateinit var currentFragment: RefreshableFragment

    override fun getLayoutID(): Int = R.layout.activity_main_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = getViewModel(this)
        this.connectToVMMessage()
        setBindings()
        setAvatar()
        setUserData()
        btnTeams.callOnClick()
//        setTestData()
    }

    fun setTestData(){
        val testList = mutableListOf<String>()
        for(int in 0..80){
            testList.add("This item is item number ${int.toString()}")
        }

        rvTeamsTest.apply {
            layoutManager = LinearLayoutManager(this@MainScreenActivity)
            adapter = RVMyTeams(
                this@MainScreenActivity,
                testList
            ){_,_ ->}
        }
    }

    private fun setBindings(){
        btnTeams.setOnClickListener {
            switchFragment(MyTeamsFragment())
            onClickButtonBottomBar(it)
        }
        btnEvents.setOnClickListener {
            switchFragment(MyEventsFragment())
            onClickButtonBottomBar(it)
        }
        btnProfile.setOnClickListener {
            switchFragment(MyDaysFragment())
            onClickButtonBottomBar(it)
        }
        btnLogout.setOnClickListener {
            Session.logout(this)
        }
    }

    private fun switchFragment(piece: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, piece)
            .runOnCommit {
                if(piece is RefreshableFragment)
                    currentFragment = piece
                else
                    throw Exception("whatcha doin here ?")
            }
            .commitAllowingStateLoss()
    }

    private fun onClickButtonBottomBar(v : View){
        bottomBar.children.forEach {
            if(it is Button)
                it.setTextColor(resources.getColor(R.color.colorFont))
        }
        if(v is Button)
            v.setTextColor(resources.getColor(R.color.colorAccent))
    }

    private fun setUserData(){
        tvUsername.text = Session.username
    }

    private fun setAvatar(){
        ivAvatar.setImageDrawable(getPlaceholderAvatar("${Session.username[0]}${if(Session.username.length >1)Session.username[1].toString() else ""}"))
    }

    private fun getPlaceholderAvatar(initials: String): TextDrawable = TextDrawable.builder()
        .beginConfig()
        .width(80)
        .height(80)
        .endConfig()
        .buildRect(initials, ContextCompat.getColor(this, R.color.colorBackground))
}