package com.mydigitalmedicaljournal.ui.supers

import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.dialog.Confirm
import com.mydigitalmedicaljournal.dialog.TextBox
import com.mydigitalmedicaljournal.json.JsonData
import com.mydigitalmedicaljournal.model.Templates

class ManagableListRecycler(json: JsonData) : RecyclerView.Adapter<ManagableListRecycler.ViewHolder>() {

    var json = json as Templates

    override fun getItemCount() = json.data.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_manage, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val title = holder.itemView.findViewById<TextView>(R.id.title)
        title.text = json.data[position].name
        bindEvents(holder, position)
    }

    private fun bindEvents(holder: ViewHolder, position: Int) {
        bindRename(holder, position)
        bindManage(holder, position)
        bindDelete(holder, position)
    }

    private fun bindDelete(holder: ViewHolder, position: Int) {
        val delete = holder.itemView.findViewById<View>(R.id.delete)
        delete.setOnClickListener {
            val alert = Confirm(
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
            var textBox = TextBox(
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