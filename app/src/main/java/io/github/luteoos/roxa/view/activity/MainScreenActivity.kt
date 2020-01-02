package io.github.luteoos.roxa.view.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.amulyakhare.textdrawable.TextDrawable
import com.bumptech.glide.Glide
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.baseAbstract.BaseActivity
import io.github.luteoos.roxa.baseAbstract.BaseViewModel
import io.github.luteoos.roxa.utils.Session
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreenActivity : BaseActivity<BaseViewModel>() {

    override fun getLayoutID(): Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = getViewModel(this)
        this.connectToVMMessage()
        setBindings()
        setAvatar()
    }

    private fun setBindings(){

    }

    private fun setAvatar(){
        ivAvatar.setImageDrawable(getPlaceholderAvatar("${Session.username[0]}${Session.username[1]}"))
    }

    private fun getPlaceholderAvatar(initials: String): TextDrawable = TextDrawable.builder()
        .beginConfig()
        .width(80)
        .height(80)
        .endConfig()
        .buildRect(initials, ContextCompat.getColor(this, R.color.colorBackground))
}