package com.mydigitalmedicaljournal.ui.templates

import android.widget.TextView
import androidx.navigation.NavController
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.NamedResource
import com.mydigitalmedicaljournal.ui._generics.NavigationalIntentRecyclerAdapter
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class TemplateMenuRecyclerAdapter(namedResourceList: MutableList<NamedResource>, private val navController: NavController) : NavigationalIntentRecyclerAdapter(namedResourceList) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.text_item).text = namedResourceList[position].getName(context)
        if (namedResourceList[position].resource != null){
            holder.itemView.setOnClickListener {
                navController.navigate(namedResourceList[position].resource!!)
            }
        }
    }

}