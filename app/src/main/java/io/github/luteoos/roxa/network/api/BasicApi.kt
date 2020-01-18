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
}