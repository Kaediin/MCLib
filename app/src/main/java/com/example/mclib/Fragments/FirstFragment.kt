package com.example.mclib.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mclib.Adapter
import com.example.mclib.Library
import com.example.mclib.R
import com.example.mclib.model.Item
import com.google.firebase.firestore.FirebaseFirestore

class FirstFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private var list = ArrayList<Item>()
    private val db = FirebaseFirestore.getInstance()


    override fun onCreateView(
    inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        list.clear()
        val inflation = inflater.inflate(R.layout.fragment_view, container, false)
        val decorator = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)

            db.collection("Items")
                .get()
                .addOnSuccessListener { results ->
                    for (result in results) {
                        val item = result["itemFile", Item::class.java]
                        if (item?.world == "Overworld") {
                            list.add(item)
                        }
                    }
                    display(decorator, inflation)
                }
                .addOnFailureListener { exception ->
                    Log.d("Firebase", "Error getting documents: ", exception)
                }
        return inflation
    }

    private fun display(decorator: DividerItemDecoration, rootView: View) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = Adapter(list, this.context!!)

        recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view).apply {
            addItemDecoration(decorator)
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}