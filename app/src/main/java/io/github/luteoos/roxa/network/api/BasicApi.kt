package io.github.luteoos.roxa.network.api

import io.github.luteoos.roxa.model.Day
import io.github.luteoos.roxa.model.Event
import io.github.luteoos.roxa.network.request.*
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface BasicApi {
    @GET("login")
    fun logIn(@Body loginRequest: LoginRequest) : Single<Response<String>>

    @GET("register")
    fun register(@Body registerRquest: RegisterRquest) : Single<Response<String>>

    @GET("tos")
    fun getToS(): Single<Response<String>>

    @POST("{userId}/group")
    fun createGroup(@Path("userId") userId: String,@Body createGroupRequest: CreateGroupRequest ) :
            Single<Response</*todo here Group model*/String>>

    @GET("{userId}/group")
    fun getMyGroups(@Path("userId") userId: String) :
            Single<Response</*todo here Group model*/String>>

    @GET("{userId}/group/{groupId}")
    fun getGroup(@Path("userId") userId: String, @Path("groupId") groupId: String) :
            Single<Response</*todo here Group model*/String>>

    @POST("{userId}/join")
    fun joinGroup(@Path("userId") userId: String, @Body joinGroupRequest: JoinGroupRequest) :
            Single<Response<String>> //todo here group entity

    @GET("{userId}/group/{groupId}/leave")
    fun leaveGroup(@Path("userId") userId: String, @Path("groupId") groupId: String) : Single<Response<Any?>>

    @GET("{userId}/group/{groupId}/days")
    fun getGroupDays(@Path("userId") userId: String, @Path("groupId") groupId: String, @Body groupDaysRequest: GroupDaysRequest) :
            Single<Response<MutableList<String>>>//here change for correct

    @GET("{userId}/group/{groupId}/myDays")
    fun getMyDays(@Path("userId") userId: String, @Path("groupId") groupId: String, @Body myDaysRequest: MyDaysRequest) :
            Single<Response<MutableList<Day>>>

    @POST("{userId}/group/{groupId}/event")
    fun createEvent(@Path("userId") userId: String, @Path("groupId") groupId: String, @Body eventRequest: UpdateEventRequest) :
            Single<Response<Any?>>

    @GET("{userId}/group/{groupId}/event")
    fun getEvents(@Path("userId") userId: String, @Path("groupId") groupId: String) :
            Single<Response<MutableList<Event>>>

    @GET("{userId}/group/{groupId}/event/{eventId}")
    fun getSingleEvent(@Path("userId") userId: String,
                       @Path("groupId") groupId: String,
                       @Path("eventId") eventId: String) :
            Single<Response<Event>>

    @POST("{userId}/group/{groupId}/event/{eventId}")
    fun updateEvent(@Path("userId") userId: String,
                       @Path("groupId") groupId: String,
                       @Path("eventId") eventId: String,
                    @Body event: Event) :
            Single<Response<Event>>// todo or simple 200 empty?
}