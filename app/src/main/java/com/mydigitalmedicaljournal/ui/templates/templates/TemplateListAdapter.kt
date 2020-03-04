package com.mydigitalmedicaljournal.ui.templates.templates

import android.util.Log
import com.mydigitalmedicaljournal.model.Templates
import com.mydigitalmedicaljournal.ui._generics.ManageableListAdapter
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateListAdapter(override var json: Templates, layout: Int) : ManageableListAdapter(json, layout) {

    override fun bindEvents(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            Log.i("TEST", "position $position clicked")
        }
    }

}
