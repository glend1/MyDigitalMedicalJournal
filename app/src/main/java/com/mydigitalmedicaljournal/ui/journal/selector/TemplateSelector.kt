package com.mydigitalmedicaljournal.ui.journal.selector

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.CategoriesAndTemplatesList
import com.mydigitalmedicaljournal.ui._generics.CustomDivider

class TemplateSelector : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_selector, container, false)
        val viewAdapter = TemplateSelectorAdapter(CategoriesAndTemplatesList(requireContext()))
        val templateList = root.findViewById<RecyclerView>(R.id.selector_recycler)
        val itemDecoration = CustomDivider(requireContext())
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        val searchView = root.findViewById<EditText>(R.id.search_view)
        val clearButton = root.findViewById<ImageView>(R.id.clear_button)
        searchView.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                clearButton.visibility = if (s.isNullOrEmpty()) { View.GONE } else { View.VISIBLE }
                viewAdapter.filterList(s)
            }
        })
        clearButton.setOnClickListener { searchView.setText("") }
        return root
    }
}
