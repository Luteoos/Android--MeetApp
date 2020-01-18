package io.github.luteoos.roxa.network.api

import io.github.luteoos.roxa.model.Event
import io.github.luteoos.roxa.model.Group
import io.github.luteoos.roxa.network.request.*
import io.github.luteoos.roxa.network.response.MyFreeTimeResponse
import io.github.luteoos.roxa.network.response.UserLoginResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface BasicApi {
    @GET("account/login")
    fun logIn(@Query("username")username: String, @Query("password") password: String) : Single<Response<UserLoginResponse>>

    @POST("account/register")
    fun register(@Query("username")username: String,@Query("mail")mail: String, @Query("password") password: String) :
            Single<Response<UserLoginResponse>>

    @POST("group/CreateGroup/{userId}")
    fun createGroup(@Path("userId")userId: String, @Query("groupName")groupName: String) : Single<Response<Void>>

    @GET("group/Listgroup/{userId}")
    fun getGroupList(@Path("userId")userId: String) : Single<Response<MutableList<Group>>>

    @POST("group/join/{userId}")
    fun joinGroup(@Path("userId")userId: String, @Query("invId")invId: String) : Single<Response<Void>>


    @POST("group/leave/{userId}")
    fun leaveGroup(@Path("userId")userId: String, @Query("groupId")groupId: String) : Single<Response<Void>>


    @POST("group/AddFreeTime/{userId}")
    fun addFreeTime(@Path("userId")userId: String, @Query("groupId")groupId: String,
                    @Query("start")startTime: String,
                    @Query("end")endTime: String) : Single<Response<Void>>

    @POST("group/DeleteFreeTime/{userId}")
    fun deleteFreeTime(@Path("userId")userId: String, @Query("freeTimeId")freeTimeId: String) : Single<Response<Void>>

    @GET("group/MyFreeTime/{userId}")
    fun getMyFreeTime(@Path("userId")userId: String) : Single<Response<MutableList<MyFreeTimeResponse>>>


    @GET("group/GetUserEvents/{userId}")
    fun getMyEvents(@Path("userId")userId: String) : Single<Response<MutableList<Event>>>


    @POST("group/CreateEvent/{userId}")
    fun createEvent(@Path("userId")userId: String, @Query("groupId")groupId: String,
                    @Body event: MultipartBody) : Single<Response<Void>>

    @POST("group/AddEventResponse/{userId}")
    fun respondToEvent(@Path("userId")userId:
                       String, @Query("eventId")eventId: String, @Query("isGoing")isGoing: Boolean) : Single<Response<Void>>
//    @GET("tos")
//    fun getToS(): Single<Response<String>>
//
//    @POST("{userId}/group")
//    fun createGroup(@Path("userId") userId: String,@Body createGroupRequest: CreateGroupRequest ) :
//            Single<Response</*todo here Group model*/String>>
//
//    @GET("{userId}/group")
//    fun getMyGroups(@Path("userId") userId: String) :
//            Single<Response</*todo here Group model*/String>>
//
//    @GET("{userId}/group/{groupId}")
//    fun getGroup(@Path("userId") userId: String, @Path("groupId") groupId: String) :
//            Single<Response</*todo here Group model*/String>>
//
//    @POST("{userId}/join")
//    fun joinGroup(@Path("userId") userId: String, @Body joinGroupRequest: JoinGroupRequest) :
//            Single<Response<String>> //todo here group entity
//
//    @GET("{userId}/group/{groupId}/leave")
//    fun leaveGroup(@Path("userId") userId: String, @Path("groupId") groupId: String) : Single<Response<Any?>>
//
//    @GET("{userId}/group/{groupId}/days")
//    fun getGroupDays(@Path("userId") userId: String, @Path("groupId") groupId: String, @Body groupDaysRequest: GroupDaysRequest) :
//            Single<Response<MutableList<String>>>//here change for correct
//
//
//    @POST("{userId}/group/{groupId}/event")
//    fun createEvent(@Path("userId") userId: String, @Path("groupId") groupId: String, @Body eventRequest: UpdateEventRequest) :
//            Single<Response<Any?>>
//
//    @GET("{userId}/group/{groupId}/event")
//    fun getEvents(@Path("userId") userId: String, @Path("groupId") groupId: String) :
//            Single<Response<MutableList<Event>>>
//
//    @GET("{userId}/group/{groupId}/event/{eventId}")
//    fun getSingleEvent(@Path("userId") userId: String,
//                       @Path("groupId") groupId: String,
//                       @Path("eventId") eventId: String) :
//            Single<Response<Event>>
//
//    @POST("{userId}/group/{groupId}/event/{eventId}")
//    fun updateEvent(@Path("userId") userId: String,
//                       @Path("groupId") groupId: String,
//                       @Path("eventId") eventId: String,
//                    @Body event: Event) :
//            Single<Response<Event>>// todo or simple 200 empty?
}