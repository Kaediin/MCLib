package com.example.mclib.model

import java.io.Serializable
import java.util.*


data class Item(
    var title_value: String,
    var x_coord_value: String,
    var y_coord_value: String,
    var z_coord_value: String,
    var world_value: String,
    var description_value: String
) : Serializable {

    constructor() : this("","","","","","")

    var title: String = title_value
    var x_coord: String = x_coord_value
    var y_coord: String = y_coord_value
    var z_coord: String = z_coord_value
    var world: String = world_value
    var description: String = description_value
}