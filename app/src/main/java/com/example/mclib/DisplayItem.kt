package com.example.mclib

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mclib.model.Item

class DisplayItem : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_item)

        val item = intent.getSerializableExtra("item_for_display") as? Item
        val displayTitle : TextView = findViewById(R.id.display_title)
        val displayx : TextView = findViewById(R.id.display_x)
        val displayy : TextView = findViewById(R.id.display_y)
        val displayz : TextView = findViewById(R.id.display_z)
        val description : TextView = findViewById(R.id.display_description)

        displayTitle.text = item!!.title
        displayx.text = "X: "+item.x_coord
        displayy.text = "Y: "+item.y_coord
        displayz.text = "Z: "+item.z_coord
        description.text = item.description
    }
}