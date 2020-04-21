package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.template.editor.TemplateView
import com.mydigitalmedicaljournal.template.file.TemplateDefinition
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class EditorAdapter(val localData: TemplateDefinition) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //TODO complete this adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(TemplateView.getView(viewType)!!.layout, parent, false)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewInt = getItemViewType(position)
        val view = TemplateView.getView(viewInt)
        view?.setup(holder.itemView, this)
    }

    override fun getItemCount(): Int = localData.data.size + 2

    override fun getItemId(position: Int): Long {
        //TODO I cant really figure out what this does
        Log.i("EDITORADAPTER", "Item ID")
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                TemplateView.NAME.id
            }
            1 -> {
                TemplateView.TIMEFORMAT.id
            }
            else -> {
                localData.data[position - 2].type.id
                //Log.i("ARRAY", viewList[position - 2].id.toString())
            }
        }
        /*return if (itemCount == position + 1) {
            Log.i("TRIGGER", "1")
            0
        } else if (templateDefinition.time == null && position == 0) {
            Log.i("TRIGGER", "2")
            1
        } else if (templateDefinition.time != null && templateDefinition.data.getOrNull(position) != null) {
            Log.i("TRIGGER", "3")
            templateDefinition.data[position].type.id
        } else {
            Log.i("TRIGGER", "4")
            templateDefinition.data[position - 1].type.id
        }*/
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
