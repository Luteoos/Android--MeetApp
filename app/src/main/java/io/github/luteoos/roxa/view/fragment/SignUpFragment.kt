package io.github.luteoos.roxa.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import io.github.luteoos.roxa.BuildConfig
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.baseAbstract.BaseFragment
import io.github.luteoos.roxa.viewmodel.SignInViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : BaseFragment<SignInViewModel>() {
    override fun getLayoutID(): Int = R.layout.fragment_sign_up

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = getViewModel(activity!!)
        setBindings()
    }

    override fun refresh() {
    }

    private fun setBindings(){
        btnSignUp.setOnClickListener {
            viewModel.sendNudes()
        }
        tvTos.text = getText(R.string.tos_accept)
        tvTos.setOnClickListener {
            openTermsOfServiceIntent()
        }
    }

    private fun openTermsOfServiceIntent(){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(BuildConfig.TOS_URL)
        if(intent.resolveActivity(activity!!.packageManager) != null)
            startActivity(intent)
    }

}