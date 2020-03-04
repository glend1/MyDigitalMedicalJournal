package com.mydigitalmedicaljournal.ui.templates.templates

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Templates
import com.mydigitalmedicaljournal.ui._generics.TextBoxDialog

class ManageTemplatesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_manage_list, container, false)
        val templates = Templates(context!!)
        val viewAdapter = TemplateListAdapter(templates, R.layout.list_item)
        val templateList = root.findViewById<RecyclerView>(R.id.recycler)
        val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        // TODO the divider here leaves a divider at the end
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            //TODO implement this method
            Log.i("ADD", "add clicked")
        }
        return root
    }
}
