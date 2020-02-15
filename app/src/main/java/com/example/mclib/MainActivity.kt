package com.example.mclib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<String>(listOf("test1", "test2", "test3"))
        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)

        viewManager = LinearLayoutManager(this)
        viewAdapter = Adapter(list, this)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            addItemDecoration(decorator)
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
