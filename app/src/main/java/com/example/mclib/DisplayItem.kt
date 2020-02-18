package com.example.mclib

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mclib.model.Item
import kotlinx.android.synthetic.main.display_item.*

class DisplayItem : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_item)

        val item = intent.getSerializableExtra("item_for_display") as? Item

        display_author.text = item?.username
        display_title.text = item?.title
        display_x.text = "X: "+item?.xCoord
        display_y.text = "Y: "+item?.yCoord
        display_z.text = "Z: "+item?.zCoord
        display_world.text = item?.world
        display_description.text = item?.description
        display_creationdate.text = item?.creationdate.toString()
    }
}