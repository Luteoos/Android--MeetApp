package io.github.luteoos.roxa.view.fragment

import android.os.Bundle
import android.view.View
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun setBindings(){
        btnSignIn.setOnClickListener {
            viewModel.sendNudes()
        }
    }

}