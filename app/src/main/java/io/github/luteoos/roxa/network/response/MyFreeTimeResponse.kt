package io.github.luteoos.roxa.network.response

import com.google.gson.annotations.SerializedName
import io.github.luteoos.roxa.model.FreeTime
import io.github.luteoos.roxa.model.User

class MyFreeTimeResponse(
    @SerializedName("GroupId")
    var groupId: String? = null,
    @SerializedName("Name")
    var groupName: String? = null,
    @SerializedName("Users")
    var freeTime: MutableList<User>? = null)