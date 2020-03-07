package com.mydigitalmedicaljournal.ui.templates.templates

import androidx.navigation.NavController
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Templates
import com.mydigitalmedicaljournal.ui._generics.ManageableListAdapter
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateListAdapter(override var json: Templates, layout: Int, private val navController: NavController) : ManageableListAdapter(json, layout) {

    override fun bindEvents(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            navController.navigate(R.id.editorFragment)
        }
    }

}
