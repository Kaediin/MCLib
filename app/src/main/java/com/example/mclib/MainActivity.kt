package com.example.mclib

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mclib.model.Item
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private var list = ArrayList<Item>()
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "MainActivity onCreate called")

        firebaseAuth = FirebaseAuth.getInstance()
        load(Provider.firstload)
        Provider.firstload = false
    }

    private fun load(isRefresh : Boolean) {
        if (Provider.allItems.isEmpty() || isRefresh) {
            list.clear()
            db.collection(Provider.world)
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
                    Log.d("MainActivity", "Error getting documents from firebase: ", exception)
                }
        } else if (Provider.allItems.size > 0) {
            setupRecyclerView()
        }
    }

    fun setupRecyclerView() {

        val fragmentAdapter = PagerAdapter(supportFragmentManager)
        viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)
        tabs_main.tabTextColors = resources.getColorStateList(android.R.color.white)

        fab.setOnClickListener {
            intent = Intent(this, CreateNewItem::class.java)
            startActivity(intent)

        }

        fab_refresh.setOnClickListener {
            load(true)
        }
    }
}
