package io.github.luteoos.roxa.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Event(@SerializedName("Id")var id: String? = null,
            @SerializedName("GroupId")var groupId: String? = null,
            @SerializedName("GroupName")var groupName: String? = null,
            @SerializedName("Name")var name: String? = null,
            @SerializedName("Description")var description: String? = null,
            @SerializedName("StartTime")var startTime: String? = null,
            @SerializedName("EndTime")var endTime: String? = null,
            @SerializedName("Localization")var localization: String? = null,
            @SerializedName("IsGoing")var isGoing: MutableList<IsGoingWrapper>? = null
) : Serializable