package io.github.luteoos.roxa.model

import java.io.Serializable

class User(var id: String,
           var name: String,
           var freeTime: MutableList<FreeTime>) : Serializable {
//
//    public string UserId { get; set; }
//    public User User { get; set; }
//    public string GroupId { get; set; }
//    public Group Group { get; set; }
}