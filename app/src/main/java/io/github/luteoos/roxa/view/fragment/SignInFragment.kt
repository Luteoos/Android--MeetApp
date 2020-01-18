package io.github.luteoos.roxa.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import es.dmoral.toasty.Toasty
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.baseAbstract.BaseFragment
import io.github.luteoos.roxa.viewmodel.SignInViewModel
import kotlinx.android.synthetic.main.fragment_sign_in.*

class SignInFragment : BaseFragment<SignInViewModel>() {

    override fun getLayoutID(): Int = R.layout.fragment_sign_in

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(activity!!)
        setBindings()
    }

    override fun refresh() {
    }

    private fun setBindings(){
        btnSignIn.setOnClickListener {
            checkSignIn()
        }
    }

    private fun checkSignIn(){
        when {
            etUsernameIN.text.isNullOrEmpty() -> Toasty.error(context!!, R.string.error_wrong_username).show()
            etPasswordIN.text.isNullOrEmpty() -> Toasty.error(context!!, R.string.error_empty_password).show()
            else -> viewModel.signIn(etUsernameIN.text.toString().replace("\\s".toRegex(),""), etPasswordIN.text.toString().replace("\\s".toRegex(),""))
        }
    }

}