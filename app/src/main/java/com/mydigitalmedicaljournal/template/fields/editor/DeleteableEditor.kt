package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import android.widget.ImageView
import com.mydigitalmedicaljournal.R

abstract class DeleteableEditor(itemView: View) : GenericEditor(itemView) {
    fun delete(listener: View.OnClickListener) {
        itemView.findViewById<ImageView>(R.id.delete).setOnClickListener(listener)
    }
}