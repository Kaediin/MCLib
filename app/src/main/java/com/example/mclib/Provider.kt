package com.example.mclib

import com.example.mclib.model.Item

class Provider {

    companion object {

        lateinit var username : String

        var allItems = ArrayList<Item>()
        var overworldItems = ArrayList<Item>()
        var netherItems = ArrayList<Item>()
        var endItems = ArrayList<Item>()

        fun addItem(item : Item){
            when(item.world){
                "Overworld" -> {
                    overworldItems.add(item)
                }
                "Nether" -> {
                    netherItems.add(item)
                }
                "End" -> {
                    endItems.add(item)
                }
            }
        }

        fun prodivde(items: ArrayList<Item> = ArrayList()) {
            allItems.clear()
            overworldItems.clear()
            netherItems.clear()
            endItems.clear()
            for (item in items) {
                when (item.world) {
                    "Overworld" -> {
                        overworldItems.add(item)
                    }
                    "Nether" -> {
                        netherItems.add(item)
                    }
                    "End" -> {
                        endItems.add(item)
                    }
                }
                allItems.add(item)
            }
        }
    }
}