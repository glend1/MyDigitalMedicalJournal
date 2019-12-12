package com.mydigitalmedicaljournal.ui.templates

import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R


class TemplatesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_templates, container, false)

        val viewAdapter = TemplateListAdapter(resources.getStringArray(R.array.template_list))
        val templateList = root.findViewById<RecyclerView>(R.id.template_list)
        val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter

            /*.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = this.layoutManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }*/
        return root
    }

    class TemplateListAdapter(private val myDataset: Array<String>) :
        RecyclerView.Adapter<TemplateListAdapter.ViewHolder>() {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
        // Each data item is just a string in this case that is shown in a TextView.
        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val textView: TextView

            init {
                // Define click listener for the ViewHolder's View.
                v.setOnClickListener { Log.d("TEXT", "Element $adapterPosition clicked.") }
                textView = v.findViewById(R.id.text_item)
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): ViewHolder {
            // create a new view
            val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)

            // set the view's size, margins, paddings and layout parameters
            return ViewHolder(layout)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.textView.text = myDataset[position]
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = myDataset.size

    }

}