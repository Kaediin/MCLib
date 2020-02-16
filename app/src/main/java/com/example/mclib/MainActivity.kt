package com.example.mclib

import android.content.Intent
import android.content.res.ColorStateList
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mclib.model.Item
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var buttonCreateNew: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("DEBUG", "MainActivity onCreate called")


        val fragmentAdapter = PagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
        tabs_main.tabTextColors = resources.getColorStateList(android.R.color.white)
        Log.d("DEBUG", "Viewpager setup")

        buttonCreateNew = findViewById(R.id.fab)

        buttonCreateNew.setOnClickListener {
            intent = Intent(this, CreateNewItem::class.java)
            startActivity(intent)
        }


    }
}
