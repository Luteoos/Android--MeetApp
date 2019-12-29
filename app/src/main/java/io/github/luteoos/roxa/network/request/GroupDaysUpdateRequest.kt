package io.github.luteoos.roxa.network.request

import io.github.luteoos.roxa.model.Day

class GroupDaysUpdateRequest(var start: String, var end: String, var list: MutableList<Day>)