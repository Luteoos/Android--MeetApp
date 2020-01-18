package io.github.luteoos.roxa.network.response

import com.google.gson.annotations.SerializedName

class UserLoginResponse(@SerializedName("Token")var token: String,
                        @SerializedName("UserName")var username: String,
                        @SerializedName("UserId")var userId: String)