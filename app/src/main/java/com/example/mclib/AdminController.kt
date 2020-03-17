package com.example.mclib

import android.content.ClipData
import android.content.Context
import android.widget.Toast
import com.example.mclib.model.Item
import com.google.firebase.firestore.FirebaseFirestore

class AdminController {
    companion object {
        fun isAdmin(): Boolean {
            val adminIDs = ArrayList<String>()
            adminIDs.add("q0uIle4WaJhNQ8xAdXVYqVltMW33")
            for (id in adminIDs){
                if (id == Provider.user.uid){
                    return true
                }
            }
            return false
        }

        fun removeItem(context: Context, item: Item){
            val db = FirebaseFirestore.getInstance()
            db.collection(Provider.world).document(item.title)
                .delete()
                .addOnSuccessListener { Toast.makeText(context, "Deleted waypoint: "+item.title, Toast.LENGTH_SHORT).show() }
                .addOnFailureListener { Toast.makeText(context, "Could not delete waypoint: "+item.title, Toast.LENGTH_SHORT).show() }

        }

    }
}