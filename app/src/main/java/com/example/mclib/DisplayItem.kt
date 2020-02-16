package com.example.mclib

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mclib.model.Item
import kotlinx.android.synthetic.main.display_item.*

class DisplayItem : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_item)

        val item = intent.getSerializableExtra("item_for_display") as? Item

        display_title.text = item!!.title
        display_x.text = "X: "+item.x_coord
        display_y.text = "Y: "+item.y_coord
        display_z.text = "Z: "+item.z_coord
        display_description.text = item.description
        display_world.text = item.world
    }
}