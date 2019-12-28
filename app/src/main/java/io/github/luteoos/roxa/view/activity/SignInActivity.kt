package io.github.luteoos.roxa.view.activity

import android.os.Bundle
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.baseAbstract.BaseActivity
import io.github.luteoos.roxa.baseAbstract.BaseViewModel

class SignInActivity : BaseActivity<BaseViewModel>() {
    override fun getLayoutID(): Int = R.layout.activity_sign_in

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = getViewModel(this)
        connectToVMMessage()
    }

    override fun onVMMessage(msg: Int?) {
        super.onVMMessage(msg)
    }
}