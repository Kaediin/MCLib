package com.example.mclib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mclib.model.Item
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var buttonCreateNew: Button
    private val db = FirebaseFirestore.getInstance()

    private val list = ArrayList<Item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCreateNew = findViewById(R.id.btn_create_item)

        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)

        db.collection("Items")
            .get()
            .addOnSuccessListener { results ->
                for (result in results) {
                    val item = result["itemFile", Item::class.java]
                    list.add(item!!)
                }
                display(decorator)
            }
            .addOnFailureListener { exception ->
                Log.d("TEST", "Error getting documents: ", exception)
            }
    }


    private fun display(decorator : DividerItemDecoration) {
        viewManager = LinearLayoutManager(this)
        viewAdapter = Adapter(list, this)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            addItemDecoration(decorator)
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        buttonCreateNew.setOnClickListener {
            intent = Intent(this, CreateNewItem::class.java)
            startActivity(intent)
        }
    }
}
