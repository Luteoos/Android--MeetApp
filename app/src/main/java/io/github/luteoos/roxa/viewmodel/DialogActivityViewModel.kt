package io.github.luteoos.roxa.viewmodel

import io.github.luteoos.roxa.baseAbstract.BaseViewModel
import io.github.luteoos.roxa.network.Rest
import io.github.luteoos.roxa.network.api.BasicApi
import io.github.luteoos.roxa.network.request.CreateEventRequest
import io.github.luteoos.roxa.utils.Parameters
import io.github.luteoos.roxa.utils.Session
import io.github.luteoos.roxa.utils.getRestDate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class DialogActivityViewModel : BaseViewModel() {

    private lateinit var teamId: String
    private var start = ""
    private var end = ""

    fun setStartDate(startDate : Calendar){
        start = startDate.getRestDate()
    }

    fun setEndDate(endDate : Calendar){
        end = endDate.getRestDate()
    }

    fun setTeamId(teamId: String){
        this.teamId = teamId
    }

    fun addFreeTime(){
        if(start == "" || end == "")
            send(Parameters.SHOW_ERROR_TOAST)
        else{
            send(Parameters.SHOW_PROGRESS_BAR)
            Rest.createService(BasicApi::class.java, Session.token).let { client ->
                disposable.add(client.addFreeTime(Session.userUUIDString, teamId, start,end)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        send(Parameters.HIDE_PROGRESS_BAR)
                        if(it.code() == 200 || it.code() == 204){
                            send(Parameters.REFRESH)
                        }
                        else{
                            send(Parameters.SHOW_ERROR_TOAST)
                        }
                    },{
                        send(Parameters.HIDE_PROGRESS_BAR)
                        send(Parameters.SHOW_ERROR_TOAST)
                    }))
            }
        }
    }

    fun addEvent(name: String, desc: String?, localization: String?){
        if(start == "" || end == "" || name.isEmpty())
            send(Parameters.SHOW_ERROR_TOAST)
        else{
            send(Parameters.SHOW_PROGRESS_BAR)
            Rest.createService(BasicApi::class.java, Session.token).let { client ->
                disposable.add(client.createEvent(Session.userUUIDString, teamId, CreateEventRequest(
                    name,desc ?: "", localization ?: "", start, end
                ))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        send(Parameters.HIDE_PROGRESS_BAR)
                        if(it.code() == 200 || it.code() == 204){
                            send(Parameters.REFRESH)
                        }
                        else{
                            send(Parameters.SHOW_ERROR_TOAST)
                        }
                    },{
                        send(Parameters.HIDE_PROGRESS_BAR)
                        send(Parameters.SHOW_ERROR_TOAST)
                    }))
            }
        }
    }

}