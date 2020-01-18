package io.github.luteoos.roxa.viewmodel

import android.util.Base64
import io.github.luteoos.roxa.baseAbstract.BaseViewModel
import io.github.luteoos.roxa.network.Rest
import io.github.luteoos.roxa.network.api.BasicApi
import io.github.luteoos.roxa.utils.Parameters
import io.github.luteoos.roxa.utils.Session
import io.github.luteoos.roxa.utils.toUUID
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.nio.charset.StandardCharsets
import java.security.MessageDigest

class SignInViewModel : BaseViewModel() {

    fun signIn(username: String, password: String){
        send(Parameters.SHOW_PROGRESS_BAR)
        Rest.createService(BasicApi::class.java).let {client ->
            disposable.add(client.logIn(username, hashPassword(password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    send(Parameters.HIDE_PROGRESS_BAR)
                    if(it.code() == 200 || it.code() == 204){
                        it.body()?.let {
                            Session.apply {
                                token = it.token
                                this.username = it.username
                                userUUID = it.userId.toUUID()
                            }
                            send(Parameters.SIGN_IN_SUCCESS)
                        }
                    }
                    else{
                        send(Parameters.SIGN_IN_FAILED)
                    }
                },{
                    send(Parameters.HIDE_PROGRESS_BAR)
                    send(Parameters.SIGN_IN_FAILED)
                }))
        }
    }

    fun createAccount(username: String, password: String, mail: String){
        send(Parameters.SHOW_PROGRESS_BAR)
        Rest.createService(BasicApi::class.java).let {client ->
            disposable.add(client.register(username, mail, hashPassword(password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    send(Parameters.HIDE_PROGRESS_BAR)
                    if(it.code() == 200 || it.code() == 204){
                        it.body()?.let {
                            Session.apply {
                                token = it.token
                                this.username = it.username
                                userUUID = it.userId.toUUID()
                            }
                            send(Parameters.SIGN_IN_SUCCESS)
                        }
                    }
                    else{
                        send(Parameters.SIGN_IN_FAILED)
                    }
                },{
                    send(Parameters.HIDE_PROGRESS_BAR)
                    send(Parameters.SIGN_IN_FAILED)
                }))
        }
    }

    fun sendNudes(){
        Session.username="SUDO NUKES"
        Session.token="I MEAN NUDES FUCK"
        send(Parameters.SIGN_IN_SUCCESS)
    }

    private fun hashPassword(password: String): String{
        MessageDigest.getInstance("SHA-256").let { hasher ->
            hasher.update((password + Parameters.SALT).toByteArray(StandardCharsets.UTF_8))
            return Base64.encodeToString(hasher.digest(), Base64.URL_SAFE)
        }
    }
}