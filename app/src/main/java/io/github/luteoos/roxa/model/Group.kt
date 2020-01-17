package io.github.luteoos.roxa.model

import java.io.Serializable

class Group(var id: String,
            var name: String,
            var invId: String,
            var users: MutableList<User>) : Serializable