package io.github.luteoos.roxa.model

import java.io.Serializable

class FreeTime(var id: String,
               var userId: String,
               var groupId: String,
               var startTime: String,
               var endTime: String) : Serializable