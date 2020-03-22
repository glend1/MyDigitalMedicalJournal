package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.model.Template
import com.mydigitalmedicaljournal.model.template.TemplateFormat
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class EditorAdapter(var templateDefinition:  MutableList<TemplateFormat>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //TODO complete this adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(TemplateView.getView(viewType)!!.layout, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val thisView = getItemViewType(position)
        TemplateView.getView(thisView)?.setEvent(holder.itemView, this)
    }

    override fun getItemCount(): Int {
        var i = templateDefinition.size + 1
        if (getTime() == null) {
            i++
        }
        return i
    }

    fun getTime(): TemplateFormat? {
        //TODO this might change as development continues
        for (template in templateDefinition) {
            if (template.type == TemplateView.TIMETYPE) {
                return template
            }
        }
        return null
    }

    override fun getItemId(position: Int): Long {
        //TODO I cant really figure out what this does
        Log.i("EDITORADAPTER", "Item ID")
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemCount == position + 1) {
            0
        } else if (getTime() == null && position == 0) {
            1
        } else if (getTime() != null && templateDefinition.getOrNull(position) != null) {
            templateDefinition[position].type.id
        } else {
            templateDefinition[position - 1].type.id
        }
    }

    /*
    root.findViewById<View>(R.id.add).setOnClickListener {
        val add = TextBoxDialog(
            getString(R.string.New),
            getString(R.string.New_Text),
            "",
            context!!
        )
        add.setListener(DialogInterface.OnClickListener { _, _ ->
            viewAdapter.json.data.add(Categories.Category(add.getText()))
            viewAdapter.json.sort()
            viewAdapter.json.save()
            viewAdapter.notifyDataSetChanged()
        })
        viewAdapter.notifyDataSetChanged()
        add.show()
    }

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

     */
}
