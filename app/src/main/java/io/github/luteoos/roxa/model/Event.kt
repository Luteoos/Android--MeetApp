package io.github.luteoos.roxa.model

import java.io.Serializable

class Event(var id: String,
            var groupId: String,
            var name: String,
            var description: String,
            var start: String,
            var end: String,
            var localization: String
) : Serializable