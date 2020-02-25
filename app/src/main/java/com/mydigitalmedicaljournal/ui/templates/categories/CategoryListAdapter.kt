package com.mydigitalmedicaljournal.ui.templates.categories

import android.content.DialogInterface
import android.util.Log
import android.view.View
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.ConfirmDialog
import com.mydigitalmedicaljournal.ui._generics.TextBoxDialog
import com.mydigitalmedicaljournal.model.Categories
import com.mydigitalmedicaljournal.ui._generics.ManageableListAdapter
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class CategoryListAdapter(override var json: Categories, layout: Int) : ManageableListAdapter(json, layout) {
    override fun bindEvents(holder: ViewHolder, position: Int) {
        bindRename(holder, position)
        bindManage(holder, position)
        bindDelete(holder, position)
    }

    private fun bindDelete(holder: ViewHolder, position: Int) {
        val delete = holder.itemView.findViewById<View>(R.id.delete)
        delete.setOnClickListener {
            val alert = ConfirmDialog(
                "Are you sure you want to delete Category named \"${json.data[position].name}\"?",
                "You will not loose any data or any saved templates. This action cannot be undone.",
                delete.context
            )
            alert.setListener(DialogInterface.OnClickListener { _, _ ->
                json.data.removeAt(position)
                json.save()
                notifyDataSetChanged()
            })
            alert.show()
        }
    }

    private fun bindManage(holder: ViewHolder, position: Int) {
        val manage = holder.itemView.findViewById<View>(R.id.manage)
        manage.setOnClickListener {
            //TODO write this method
            Log.i("MANAGE", position.toString())
        }
    }

    private fun bindRename(holder: ViewHolder, position: Int) {
        val rename = holder.itemView.findViewById<View>(R.id.rename)
        rename.setOnClickListener {
            val textBox = TextBoxDialog(
                "Rename",
                "Please type the new name for \"${json.data[position].name}\"",
                json.data[position].name,
                rename.context
            )
            textBox.setListener(DialogInterface.OnClickListener { _, _ ->
                json.data[position].name = textBox.getText()
                json.sort()
                json.save()
                notifyDataSetChanged()
            })
            textBox.show()
        }
    }
}
