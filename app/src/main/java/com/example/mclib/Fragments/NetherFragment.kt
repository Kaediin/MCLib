package com.example.mclib.Fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mclib.Adapter
import com.example.mclib.Provider
import com.example.mclib.R
import kotlinx.android.synthetic.main.list_item.*

class NetherFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflation = inflater.inflate(R.layout.fragment_view, container, false)

        display(inflation)

        return inflation
    }

    private fun display(rootView: View) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = Adapter(Provider.netherItems, this.context!!)

        recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}