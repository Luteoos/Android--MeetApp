package io.github.luteoos.roxa.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
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
import io.github.luteoos.roxa.utils.Parameters
import io.github.luteoos.roxa.utils.Parameters.OPEN_DIALOG_ACTIVITY

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
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == OPEN_DIALOG_ACTIVITY)
            currentFragment.refresh()
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onVMMessage(msg: Int?) {
        super.onVMMessage(msg)
        when(msg){
            Parameters.SHOW_PROGRESS_BAR -> progressBarVisibility(true)
            Parameters.HIDE_PROGRESS_BAR -> progressBarVisibility(false)
        }
    }

    private fun progressBarVisibility(visible: Boolean){
        progressBar.visibility = if(visible) View.VISIBLE else View.GONE
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
        btnRefresh.setOnClickListener {
            currentFragment.refresh()
            it.animate().rotation(it.rotation + 360f).interpolator =
                AccelerateDecelerateInterpolator()
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
        ivAvatar.setImageDrawable(getPlaceholderAvatar("${Session.username}"))//${if(Session.username.length >1)Session.username[1].toString() else ""}"))
    }

    private fun getPlaceholderAvatar(initials: String): TextDrawable = TextDrawable.builder()
        .beginConfig()
        .bold()
        .fontSize(30)
        .endConfig()
        .buildRound(initials, ContextCompat.getColor(this, R.color.colorBackground))
}