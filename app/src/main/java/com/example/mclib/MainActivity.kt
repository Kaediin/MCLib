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
    private val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "MainActivity onCreate called")

        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun load(isRefresh : Boolean) {
        if (Provider.allItems.isEmpty() || isRefresh) {
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
                    Log.d("MainActivity", "Error getting documents from firebase: ", exception)
                }
        } else if (Provider.allItems.size > 0) {
            setupRecyclerView()
        }
    }

    override fun onStart() {
        super.onStart()

        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            Provider.username = user.displayName!!
            load(false)
        } else {
            configureGoogleSignIn()
            signIn()
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                Snackbar.make(
                    rel_layout_main,
                    "Welcome",
                    Snackbar.LENGTH_LONG
                ).show()
                load(true)
            } else {
                Snackbar.make(rel_layout_main, "Google sign in failed:(", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    firebaseAuthWithGoogle(account)
                }
            } catch (e: ApiException) {
                Snackbar.make(
                    rel_layout_main,
                    "Please sign in to use this app",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
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
