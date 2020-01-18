package io.github.luteoos.roxa.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import es.dmoral.toasty.Toasty
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.baseAbstract.BaseActivity
import io.github.luteoos.roxa.utils.Parameters
import io.github.luteoos.roxa.view.fragment.SignInFragment
import io.github.luteoos.roxa.view.fragment.SignUpFragment
import io.github.luteoos.roxa.viewmodel.SignInViewModel
import kotlinx.android.synthetic.main.activity_main_screen.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity<SignInViewModel>() {
    override fun getLayoutID(): Int = R.layout.activity_sign_in

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.viewModel = getViewModel(this)
        connectToVMMessage()
        setBindings()
        changeToSignIn()
    }

    override fun onVMMessage(msg: Int?) {
        super.onVMMessage(msg)
        when(msg){
            Parameters.SIGN_IN_SUCCESS -> onSignInSuccess()
            Parameters.SIGN_IN_FAILED -> Toasty.error(this, R.string.error).show()
            Parameters.SHOW_PROGRESS_BAR -> progressBarVisibility(true)
            Parameters.HIDE_PROGRESS_BAR -> progressBarVisibility(false)
        }
    }

    private fun setBindings(){
        tvSignIn.setOnClickListener {
            changeToSignIn()
        }
        tvSignUp.setOnClickListener {
            changeToSignIn(false)
        }
    }

    private fun progressBarVisibility(visible: Boolean){
        progressBarSign.visibility = if(visible) View.VISIBLE else View.GONE
    }

    private fun switchFragments(piece: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.signFragment, piece)
            .commitAllowingStateLoss()
    }

    private fun changeToSignIn(signIn: Boolean = true){
        if(signIn){
            tvSignUp.visibility = View.VISIBLE
            tvSignIn.visibility = View.GONE
            switchFragments(SignInFragment())
        }
        else{
            tvSignUp.visibility = View.GONE
            tvSignIn.visibility = View.VISIBLE
            switchFragments(SignUpFragment())
        }
    }

    private fun onSignInSuccess(){
        val intent = Intent(this, MainScreenActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}