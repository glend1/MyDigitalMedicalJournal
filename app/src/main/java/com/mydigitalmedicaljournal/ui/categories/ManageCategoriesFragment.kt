package com.mydigitalmedicaljournal.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Categories
import com.mydigitalmedicaljournal.ui._generics.CustomDivider
import com.mydigitalmedicaljournal.ui._generics.EmptyAdapter
import com.mydigitalmedicaljournal.ui._generics.dialogs.ConfirmDialog
import com.mydigitalmedicaljournal.ui._generics.dialogs.textbox.TextBoxDialog

class ManageCategoriesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_categories, container, false)
        val categories = Categories(requireContext())
        val viewAdapter = Adapter(categories, container)
        val templateList = root.findViewById<RecyclerView>(R.id.recycler)
        val itemDecoration = CustomDivider(requireContext())
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            val add =
                TextBoxDialog(
                    getString(R.string.new_value, getString(R.string.category)),
                    getString(R.string.new_category_text),
                    "",
                    requireContext()
                )
            add.setConfirm { _, _ ->
                viewAdapter.json.add(Categories.Category(add.getText()))
                viewAdapter.notifyDataSetChanged()
            }
            add.show()
        }
        return root

    }

    class Adapter(var json: Categories, private val container: ViewGroup?) : EmptyAdapter() {
        override val layout = R.layout.list_manage
        override val message =  container!!.context.getString(R.string.no_data, container.context.getString(R.string.category))
        override fun getItemCount() = if (isEmpty()) { 1 } else { json.size() }
        override fun isEmpty() = json.size() <= 0

        override fun bindView(view: View, position: Int) {
            view.findViewById<TextView>(R.id.text).text = json.getName(position)
            bindRename(view, position)
            bindManage(view, position)
            bindDelete(view, position)
        }

        private fun bindDelete(view: View, position: Int) {
            val delete = view.findViewById<View>(R.id.delete)
            delete.setOnClickListener {
                val alert =
                    ConfirmDialog(
                        delete.context.getString(R.string.confirm_delete, delete.context.getString(R.string.category), json.getName(position)),
                        delete.context.getString(R.string.confirm_category_warning),
                        delete.context
                    )
                alert.setConfirm { _, _ ->
                    json.remove(position)
                    notifyDataSetChanged()
                }
                alert.show()
            }
        }

        private fun bindManage(view: View, position: Int) {
            val manage = view.findViewById<View>(R.id.manage)
            manage.setOnClickListener {
                val bundle = bundleOf("categoryPosition" to position)
                container?.findNavController()?.navigate(R.id.manageCategoryTemplatesFragment, bundle)
            }
        }

        private fun bindRename(view: View, position: Int) {
            val rename = view.findViewById<View>(R.id.rename)
            rename.setOnClickListener {
                val textBox =
                    TextBoxDialog(
                        rename.context.getString(R.string.rename),
                        rename.context.getString(R.string.rename_text, json.getName(position)),
                        json.getName(position),
                        rename.context
                    )
                textBox.setConfirm { _, _ ->
                    json.setName(position, textBox.getText())
                    notifyDataSetChanged()
                }
                textBox.show()
            }
        }
    }
}

