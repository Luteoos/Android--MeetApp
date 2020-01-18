package io.github.luteoos.roxa.utils

import io.github.luteoos.roxa.network.request.CreateEventRequest
import okhttp3.MultipartBody

object MultipartBodyFiller {

    fun getMultipartEventBody(event: CreateEventRequest) : MultipartBody =
        MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("Name", event.Name ?: "")
            .addFormDataPart("Description", event.Description ?: "")
            .addFormDataPart("StartTime", event.StartTime ?: "")
            .addFormDataPart("EndTime", event.EndTime ?: "")
            .addFormDataPart("Localization", event.Localization ?: "")
            .build()

}