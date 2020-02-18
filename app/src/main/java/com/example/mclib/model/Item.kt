package com.example.mclib.model

import java.io.Serializable
import java.util.*


data class Item(
    var title: String,
    var xCoord: String,
    var yCoord: String,
    var zCoord: String,
    var world: String,
    var description: String,
    var creationdate : Date,
    var username : String
) : Serializable {
    constructor() : this("", "", "", "", "", "", Calendar.getInstance().time, "Anonymous user")
}
