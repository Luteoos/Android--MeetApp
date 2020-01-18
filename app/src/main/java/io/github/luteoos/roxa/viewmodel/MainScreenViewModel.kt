package io.github.luteoos.roxa.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.luteoos.roxa.baseAbstract.BaseViewModel
import io.github.luteoos.roxa.model.Event
import io.github.luteoos.roxa.model.Group
import io.github.luteoos.roxa.network.Rest
import io.github.luteoos.roxa.network.api.BasicApi
import io.github.luteoos.roxa.network.response.MyFreeTimeResponse
import io.github.luteoos.roxa.utils.Parameters
import io.github.luteoos.roxa.utils.Session
import io.github.luteoos.roxa.utils.toUUID
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainScreenViewModel : BaseViewModel() {

    private val teamsList : MutableLiveData<MutableList<Group>> = MutableLiveData()
    fun getTeamsLiveData() : LiveData<MutableList<Group>> = teamsList

    private val eventsList : MutableLiveData<MutableList<Event>> = MutableLiveData()
    fun getEventsLiveData() : LiveData<MutableList<Event>> = eventsList

    private val freeTimeList : MutableLiveData<MutableList<MyFreeTimeResponse>> = MutableLiveData()
    fun getFreeTimeLiveData() : LiveData<MutableList<MyFreeTimeResponse>> = freeTimeList

    fun getFreeTime(){
        send(Parameters.SHOW_PROGRESS_BAR)
        Rest.createService(BasicApi::class.java, Session.token).let { client ->
            disposable.add(client.getMyFreeTime(Session.userUUIDString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    send(Parameters.HIDE_PROGRESS_BAR)
                    if(it.code() == 200 || it.code() == 204){
                        it.body()?.let {
                            freeTimeList.value = it
                        }
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

    fun getEvents(){
        send(Parameters.SHOW_PROGRESS_BAR)
        Rest.createService(BasicApi::class.java, Session.token).let { client ->
            disposable.add(client.getMyEvents(Session.userUUIDString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    send(Parameters.HIDE_PROGRESS_BAR)
                    if(it.code() == 200 || it.code() == 204){
                        it.body()?.let {
                            eventsList.value = it
                        }
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

    fun getTeams(){
        send(Parameters.SHOW_PROGRESS_BAR)
        Rest.createService(BasicApi::class.java, Session.token).let { client ->
            disposable.add(client.getGroupList(Session.userUUIDString)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    send(Parameters.HIDE_PROGRESS_BAR)
                    if(it.code() == 200 || it.code() == 204){
                        it.body()?.let {
                            teamsList.value = it
                        }
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

    fun deleteFreeTime(freeTimeId: String){
        send(Parameters.SHOW_PROGRESS_BAR)
        Rest.createService(BasicApi::class.java, Session.token).let { client ->
            disposable.add(client.deleteFreeTime(Session.userUUIDString, freeTimeId)
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

    fun joinTeam(invitation: String){
        send(Parameters.SHOW_PROGRESS_BAR)
        Rest.createService(BasicApi::class.java, Session.token).let { client ->
            disposable.add(client.joinGroup(Session.userUUIDString, invitation)
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

    fun leaveTeam(teamId: String){
        send(Parameters.SHOW_PROGRESS_BAR)
        Rest.createService(BasicApi::class.java, Session.token).let { client ->
            disposable.add(client.leaveGroup(Session.userUUIDString, teamId)
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

    fun createTeam(teamName: String){
        send(Parameters.SHOW_PROGRESS_BAR)
        Rest.createService(BasicApi::class.java, Session.token).let { client ->
            disposable.add(client.createGroup(Session.userUUIDString, teamName)
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

    fun respondToEvent(eventId: String, isGoing: Boolean){
        send(Parameters.SHOW_PROGRESS_BAR)
        Rest.createService(BasicApi::class.java, Session.token).let { client ->
            disposable.add(client.respondToEvent(Session.userUUIDString, eventId, isGoing)
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