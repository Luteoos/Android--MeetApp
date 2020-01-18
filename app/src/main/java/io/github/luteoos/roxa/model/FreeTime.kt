package io.github.luteoos.roxa.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FreeTime(@SerializedName("Id")var id: String? = null,
               @SerializedName("UserId")var userId: String? = null,
               @SerializedName("GroupId")var groupId: String? = null,
               @SerializedName("StartTime")var startTime: String? = null,
               @SerializedName("EndTime")var endTime: String? = null) : Serializable