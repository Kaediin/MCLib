package com.example.mclib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mclib.model.Item
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var buttonCreateNew: FloatingActionButton
    private val db = FirebaseFirestore.getInstance()
    private var list = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("DEBUG", "MainActivity onCreate called")

        if(Provider.allItems.isEmpty()) {
            list.clear()
            db.collection("Items")
                .get()
                .addOnSuccessListener { results ->
                    for (result in results) {
                        val item = result["itemFile", Item::class.java]
                        if (item != null) {
                            list.add(item)
                        }
                    }
                    Provider.prodivde(list)
                    setupRecyclerView()
                }
                .addOnFailureListener { exception ->
                    Log.d("Firebase", "Error getting documents: ", exception)
                }
        } else if (Provider.allItems.size > 0){
            setupRecyclerView()
        }


    }

    fun setupRecyclerView() {

        val fragmentAdapter = PagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
        tabs_main.tabTextColors = resources.getColorStateList(android.R.color.white)

        buttonCreateNew = findViewById(R.id.fab)

        buttonCreateNew.setOnClickListener {
            intent = Intent(this, CreateNewItem::class.java)
            startActivity(intent)
        }


    }
}
