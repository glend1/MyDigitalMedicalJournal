package com.mydigitalmedicaljournal.ui.categories

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Categories
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.dialogs.TextBoxDialog
import com.mydigitalmedicaljournal.ui._generics.dialogs.ConfirmDialog
import com.mydigitalmedicaljournal.ui._generics.dialogs.ListDialog
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class CategoryListAdapter(var json: Categories, val layout: Int) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount() = json.size()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = holder.itemView.findViewById<TextView>(R.id.text)
        title.text = json.getName(position)
        bindRename(holder, position)
        bindManage(holder, position)
        bindDelete(holder, position)
    }

    private fun bindDelete(holder: ViewHolder, position: Int) {
        val delete = holder.itemView.findViewById<View>(R.id.delete)
        delete.setOnClickListener {
            val alert =
                ConfirmDialog(
                    delete.context.getString(R.string.Confirm_Category, json.getName(position)),
                    delete.context.getString(R.string.Confirm_Category_Warning),
                    delete.context
                )
            alert.setConfirm(DialogInterface.OnClickListener { _, _ ->
                json.remove(position)
                notifyDataSetChanged()
            })
            alert.show()
        }
    }

    private fun bindManage(holder: ViewHolder, position: Int) {
        val manage = holder.itemView.findViewById<View>(R.id.manage)
        manage.setOnClickListener {
            val templateList = TemplateList.getTemplates(manage.context, arrayOf("templates"))
            val adapter =
                ManageCategoriesAdapter(
                    templateList,
                    json.getTemplate(position)
                )
            val listDialog = ListDialog(
                    manage.context.getString(R.string.Manage),
                    manage.context.getString(R.string.Manage_Text, json.getName(position)),
                    manage.context,
                    adapter
                )
            listDialog.setConfirm(DialogInterface.OnClickListener { _, _ ->
                json.setTemplate(position, adapter.localData)
            })
            listDialog.show()
        }
    }

    private fun bindRename(holder: ViewHolder, position: Int) {
        val rename = holder.itemView.findViewById<View>(R.id.rename)
        rename.setOnClickListener {
            val textBox =
                TextBoxDialog(
                    rename.context.getString(R.string.Rename),
                    rename.context.getString(R.string.Rename_Text, json.getName(position)),
                    json.getName(position),
                    rename.context
                )
            textBox.setConfirm(DialogInterface.OnClickListener { _, _ ->
                json.setName(position, textBox.getText())
                notifyDataSetChanged()
            })
            textBox.show()
        }
    }
}