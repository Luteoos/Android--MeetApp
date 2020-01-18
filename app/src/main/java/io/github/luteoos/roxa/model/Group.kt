package io.github.luteoos.roxa.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Group(@SerializedName("GroupId")var id: String? = null,
            @SerializedName("Name")var name: String? = null,
            @SerializedName("InvId")var invId: String? = null,
            @SerializedName("Users")var users: MutableList<User>? = null) : Serializable