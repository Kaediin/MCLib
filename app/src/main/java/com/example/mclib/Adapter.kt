package com.example.mclib

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val dataSet : ArrayList<String>, private val context: Context) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val textView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        ) as TextView

        return ViewHolder(textView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet[position]

        holder.textView.setOnClickListener {
            Toast.makeText(context, "Loading: "+dataSet[position], Toast.LENGTH_SHORT).show()

        }
    }

    override fun getItemCount() = dataSet.size
}