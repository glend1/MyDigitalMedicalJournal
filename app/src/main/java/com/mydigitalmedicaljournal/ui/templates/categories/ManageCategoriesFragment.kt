package com.mydigitalmedicaljournal.ui.templates.categories

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui._generics.TextBoxDialog
import com.mydigitalmedicaljournal.model.Categories

class ManageCategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_manage_list, container, false)
        val categories = Categories(context!!)
        val viewAdapter = CategoryListAdapter(categories, R.layout.list_manage)
        val templateList = root.findViewById<RecyclerView>(R.id.recycler)
        // TODO the divider here leaves a divider at the end
        val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            val add = TextBoxDialog(
                "Add New Template",
                "Please type the name for the new template",
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
        return root

    }
}

