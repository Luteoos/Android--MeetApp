package io.github.luteoos.roxa.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User(@SerializedName("Id")var id: String? = null,
           @SerializedName("Name")var name: String? = null,
           @SerializedName("UserFreeTimes")var freeTime: MutableList<FreeTime>? = null)
