package com.example.mclib

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mclib.model.Item

class Adapter(private val dataSet : ArrayList<Item>, private val context: Context) :
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
        holder.textView.text = dataSet[position].title

        holder.textView.setOnClickListener {
            val intent = Intent(context, DisplayItem::class.java)
            intent.putExtra("item_for_display",  dataSet[position])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataSet.size
}