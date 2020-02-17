package com.example.mclib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.mclib.model.Item
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.create_item.*
import java.util.*

class CreateNewItem : AppCompatActivity() {

//    private lateinit var et_title : EditText
//    private lateinit var et_x_coord : EditText
//    private lateinit var et_y_coord : EditText
//    private lateinit var et_z_coord : EditText
//    private lateinit var et_description : EditText
//    private lateinit var button_save : Button
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_item)

        val spinner : Spinner = findViewById(R.id.et_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.catergory_array,
            R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(R.layout.spinner_item)
            spinner.adapter = adapter
        }

        btn_save_item.setOnClickListener {
            save_item()
        }

    }

    private fun save_item() {
        var isFulledIn = true

        if (et_titel.text.toString().isBlank() || et_titel.text.toString().isEmpty()){
            isFulledIn = false
        } else if (et_x_coord.text.toString().isBlank() || et_x_coord.text.toString().isEmpty()){
            isFulledIn = false
        } else if (et_y_coord.text.toString().isBlank() || et_y_coord.text.toString().isEmpty()){
            isFulledIn = false
        } else if (et_z_coord.text.toString().isBlank() || et_z_coord.text.toString().isEmpty()){
            isFulledIn = false
        }

        if (isFulledIn){

            val date = Calendar.getInstance().time

            val item = Item(
                et_titel.text.toString(),
                et_x_coord.text.toString(),
                et_y_coord.text.toString(),
                et_z_coord.text.toString(),
                et_spinner.selectedItem.toString(),
                et_description.text.toString()
                )

            val exportMap = hashMapOf(
                "title" to item.title,
                "itemFile" to item
            )

            db.collection("Items").document(item.title)
                .set(exportMap)
                .addOnSuccessListener {
                    Log.d("TEST", "DocumentSnapshot successfully written!")
                }
                .addOnFailureListener { e -> Log.w("TEST", "Error writing document", e) }

            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        } else{
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show()
        }

    }
}