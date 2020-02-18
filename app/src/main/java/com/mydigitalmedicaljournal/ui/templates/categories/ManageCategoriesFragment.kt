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
import com.mydigitalmedicaljournal.dialog.TextBox
import com.mydigitalmedicaljournal.model.Categories


class ManageCategoriesFragment : Fragment() {

    private var add: TextBox? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_manage_categories, container, false)
        val cat = Categories(context!!)
        val viewAdapter = CategoryRecyclerAdapter(cat)
        val templateList = root.findViewById<RecyclerView>(R.id.template_manage_category)
        val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            val listener = DialogInterface.OnClickListener { _, _ ->
                viewAdapter.cat.data.add(Categories.Category(add!!.getText()))
                viewAdapter.cat.sort()
                viewAdapter.cat.save()
                viewAdapter.notifyDataSetChanged()
            }
            add = TextBox(
                "Add New Category",
                "Please type the name for the new category",
                "",
                listener,
                context!!
            )
            viewAdapter.notifyDataSetChanged()
            add?.show()
        }
        return root

    }
}

