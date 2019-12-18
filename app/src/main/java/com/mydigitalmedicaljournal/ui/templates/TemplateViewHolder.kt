package com.mydigitalmedicaljournal.ui.templates

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R

class TemplateViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    val textView: TextView = v.findViewById(R.id.text_item)
}