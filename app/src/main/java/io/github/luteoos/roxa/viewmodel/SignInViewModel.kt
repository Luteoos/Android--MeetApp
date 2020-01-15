package io.github.luteoos.roxa.viewmodel

import io.github.luteoos.roxa.baseAbstract.BaseViewModel
import io.github.luteoos.roxa.utils.Parameters
import io.github.luteoos.roxa.utils.Session

class SignInViewModel : BaseViewModel() {

    fun sendNudes(){
        Session.username="SUDO NUKES"
        Session.token="I MEAN NUDES FUCK"
        send(Parameters.SIGN_IN_SUCCESS)
    }
}