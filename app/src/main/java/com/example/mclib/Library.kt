package com.example.mclib

import com.example.mclib.model.Item

class Library {

    public var allItems = ArrayList<Item>()
    public var allIOverworldtems = ArrayList<Item>()
    public var allINethertems = ArrayList<Item>()
    public var allIEndtems = ArrayList<Item>()

    fun fillLists(list: ArrayList<Item>) {
        allItems = list
        for (item in list) {
            if (item.world == "Overworld") {
                allIOverworldtems.add(item)
            } else if (item.world == "Nether") {
                allINethertems.add(item)
            } else {
                allIEndtems.add(item)
            }
        }
    }
}