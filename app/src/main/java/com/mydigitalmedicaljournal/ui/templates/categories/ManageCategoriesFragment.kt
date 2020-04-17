package com.mydigitalmedicaljournal.ui.templates.categories

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Categories
import com.mydigitalmedicaljournal.ui._generics.CustomDivider
import com.mydigitalmedicaljournal.ui._generics.dialogs.TextBoxDialog

class ManageCategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_categories, container, false)
        val categories = Categories(context!!)
        val viewAdapter = CategoryListAdapter(categories, R.layout.list_manage)
        val templateList = root.findViewById<RecyclerView>(R.id.recycler)
        val itemDecoration = CustomDivider(context!!)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            val add =
                TextBoxDialog(
                    getString(R.string.New),
                    getString(R.string.New_Text),
                    "",
                    context!!
                )
            add.setConfirm(DialogInterface.OnClickListener { _, _ ->
                viewAdapter.json.add(Categories.Category(add.getText()))
                viewAdapter.notifyDataSetChanged()
            })
            viewAdapter.notifyDataSetChanged()
            add.show()
        }
        return root

    }
}

