package com.mydigitalmedicaljournal.ui.templates.categories

import android.content.DialogInterface
import android.view.View
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Categories
import com.mydigitalmedicaljournal.model.Templates
import com.mydigitalmedicaljournal.ui._generics.*

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
                delete.context.getString(R.string.Confirm, json.data[position].name),
                delete.context.getString(R.string.Confirm_Warning),
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
            val adapter =
                ManageCategoriesAdapter(
                    Templates(manage.context),
                    json.data[position].templates
                )
            val listDialog = ListDialog(
                manage.context.getString(R.string.Manage),
                manage.context.getString(R.string.Manage_Text, json.data[position].name),
                manage.context,
                adapter
                )
            listDialog.setListener(DialogInterface.OnClickListener { _, _ ->
                json.data[position].templates = adapter.localData
                json.save()
            })
            listDialog.show()
        }
    }

    private fun bindRename(holder: ViewHolder, position: Int) {
        val rename = holder.itemView.findViewById<View>(R.id.rename)
        rename.setOnClickListener {
            val textBox = TextBoxDialog(
                rename.context.getString(R.string.Rename),
                rename.context.getString(R.string.Rename_Text, json.data[position].name),
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
