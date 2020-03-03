package com.mydigitalmedicaljournal.ui._generics

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.NamedResource

abstract class NavigationalIntentRecyclerAdapter(var namedResourceList: MutableList<NamedResource>) : RecyclerView.Adapter<ViewHolder>() {

    protected lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(layout)
    }

    abstract override fun onBindViewHolder(holder: ViewHolder, position: Int)

    override fun getItemCount() = namedResourceList.size
}