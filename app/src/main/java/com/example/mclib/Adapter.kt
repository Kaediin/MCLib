package com.example.mclib

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mclib.model.Item
import kotlinx.android.synthetic.main.list_item.view.*

class Adapter(private val dataSet : ArrayList<Item>, private val context: Context) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val relativeLayout: RelativeLayout) : RecyclerView.ViewHolder(relativeLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val rel = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        ) as RelativeLayout

        return ViewHolder(rel)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.relativeLayout.tv_list_item.text = dataSet[position].title

        holder.relativeLayout.tv_list_item.setOnClickListener {
            val intent = Intent(context, DisplayItem::class.java)
            intent.putExtra("item_for_display",  dataSet[position])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataSet.size
}