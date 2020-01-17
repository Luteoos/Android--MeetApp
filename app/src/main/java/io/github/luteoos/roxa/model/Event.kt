package io.github.luteoos.roxa.model

import java.io.Serializable

class Event(var id: String,
            var groupId: String,
            var groupName: String,
            var name: String,
            var description: String,
            var startTime: String,
            var endTime: String,
            var localization: String,
            var isGoing: MutableList<IsGoingWrapper>
) : Serializable