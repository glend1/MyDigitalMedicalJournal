package com.mydigitalmedicaljournal.ui._generics.dialogs.input

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.ViewHolder

class DialogInputList(builder: AlertDialog.Builder, private val context: Context, data: Array<Int>, container: ViewGroup? ) : AbstractDialogInput {
    var listener: ((Int) -> Unit?)? = null
    private val view: View = LayoutInflater.from(context).inflate(R.layout.recycler_scroll, container, false)

    init {
        view.findViewById<RecyclerView>(R.id.recycler).adapter = DialogListAdapter(data)
        builder.setView(view)
    }

    inner class DialogListAdapter(val data: Array<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun getItemCount() = data.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
            return ViewHolder(layout)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val tv = holder.itemView.findViewById<TextView>(R.id.text)
            tv.text = context.resources.getText(data[position])
            holder.itemView.setOnClickListener {
                listener?.invoke(data[position])
            }
        }
    }
}
