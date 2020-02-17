package com.example.mclib.model

import java.io.Serializable


data class Item(
    var title: String,
    var xCoord: String,
    var yCoord: String,
    var zCoord: String,
    var world: String,
    var description: String
) : Serializable {
    constructor() : this("", "", "", "", "", "")
}
