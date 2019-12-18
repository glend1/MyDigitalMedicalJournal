package com.mydigitalmedicaljournal.ui.templates

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R

// Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder.
// Each data item is just a string in this case that is shown in a TextView.
class TemplateViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val textView: TextView = v.findViewById(R.id.text_item)
}