package com.example.mclib

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mclib.model.Item
import kotlinx.android.synthetic.main.dialog_delete_item.view.*
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

        if (AdminController.isAdmin()) {
            holder.relativeLayout.delete_item.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                val layoutInflater = LayoutInflater.from(context)
                val view = layoutInflater.inflate(R.layout.dialog_delete_item, null)
                builder.setView(view)
                val dialog = builder.create()
                dialog.show()
                view.to_be_deleted_item.text = dataSet[position].title
                view.delete_item_button.setOnClickListener {
                    dialog.dismiss()
                    AdminController.removeItem(context, dataSet[position])
                    dataSet.remove(dataSet[position])
                    this.notifyDataSetChanged()
                }

            }
        } else{
            holder.relativeLayout.delete_item.visibility = View.GONE
        }
        holder.relativeLayout.tv_list_item.setOnClickListener {
            val intent = Intent(context, DisplayItem::class.java)
            intent.putExtra("item_for_display",  dataSet[position])
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = dataSet.size
}