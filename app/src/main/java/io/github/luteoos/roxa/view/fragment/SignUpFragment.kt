package io.github.luteoos.roxa.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.View
import es.dmoral.toasty.Toasty
import io.github.luteoos.roxa.BuildConfig
import io.github.luteoos.roxa.R
import io.github.luteoos.roxa.baseAbstract.BaseFragment
import io.github.luteoos.roxa.viewmodel.SignInViewModel
import kotlinx.android.synthetic.main.fragment_sign_up.*
import java.math.BigInteger
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*

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
            checkSignUp()
        }
        tvTos.text = getText(R.string.tos_accept)
        tvTos.setOnClickListener {
            openTermsOfServiceIntent()
        }
    }

    private fun checkSignUp(){
        if(etMail.text.isNullOrEmpty() || !etMail.text.contains("@"))
            Toasty.error(context!!, R.string.error_wrong_mail).show()
        else{
            if(etPassword.text.isNullOrEmpty())
                Toasty.error(context!!, R.string.error_empty_password).show()
            else{
                if(etUsername.text.isNullOrEmpty())
                    Toasty.error(context!!, R.string.error_wrong_username).show()
                else{
                    if(!checkBoxTos.isChecked)
                        Toasty.error(context!!, R.string.error_wrong_noToS).show()
                    else
                        viewModel.createAccount(etUsername.text.toString().replace("\\s".toRegex(),""),
                            etPassword.text.toString().replace("\\s".toRegex(),""),
                            etMail.text.toString().replace("\\s".toRegex(),""))
                }
            }
        }
    }

    private fun openTermsOfServiceIntent(){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(BuildConfig.TOS_URL)
        if(intent.resolveActivity(activity!!.packageManager) != null)
            startActivity(intent)
    }

}