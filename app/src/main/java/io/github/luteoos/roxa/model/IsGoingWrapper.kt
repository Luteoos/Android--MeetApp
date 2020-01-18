package io.github.luteoos.roxa.model

import com.google.gson.annotations.SerializedName

class IsGoingWrapper(@SerializedName("UserId")var userId: String? = null,
                     @SerializedName("UserName")var username: String? = null,
                     @SerializedName("IsGoing")var isGoing: Boolean? = null) {
}