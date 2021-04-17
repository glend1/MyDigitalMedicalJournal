package com.mydigitalmedicaljournal.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.model.Categories
import com.mydigitalmedicaljournal.template.file.TemplateList
import com.mydigitalmedicaljournal.ui._generics.CustomDivider
import com.mydigitalmedicaljournal.ui._generics.ViewHolder
import com.mydigitalmedicaljournal.ui._generics.dialogs.ConfirmDialog
import com.mydigitalmedicaljournal.ui._generics.dialogs.textbox.TextBoxDialog
import com.mydigitalmedicaljournal.ui.categories.dialog.ManageCategoryTemplatesDialog

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
                    getString(R.string.New),
                    getString(R.string.New_Text),
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

    class Adapter(var json: Categories, private val container: ViewGroup?) : RecyclerView.Adapter<ViewHolder>() {
        private fun isEmpty(): Boolean {
            return json.size() <= 0
        }

        override fun getItemCount() = if (isEmpty()) { 1 } else { json.size() }

        override fun getItemViewType(position: Int): Int {
            return if (isEmpty()) { R.layout.empty_recycler } else { R.layout.list_manage }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layout = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
            return ViewHolder(layout)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = holder.itemView
            if (isEmpty()) {
                item.findViewById<TextView>(R.id.message).text = item.resources.getString(R.string.no_categories)
            } else {
                item.findViewById<TextView>(R.id.text).text = json.getName(position)
                bindRename(holder, position)
                bindManage(holder, position)
                bindDelete(holder, position)
            }
        }

        private fun bindDelete(holder: ViewHolder, position: Int) {
            val delete = holder.itemView.findViewById<View>(R.id.delete)
            delete.setOnClickListener {
                val alert =
                    ConfirmDialog(
                        delete.context.getString(R.string.Confirm_Category, json.getName(position)),
                        delete.context.getString(R.string.Confirm_Category_Warning),
                        delete.context
                    )
                alert.setConfirm { _, _ ->
                    json.remove(position)
                    notifyDataSetChanged()
                }
                alert.show()
            }
        }

        private fun bindManage(holder: ViewHolder, position: Int) {
            val manage = holder.itemView.findViewById<View>(R.id.manage)
            manage.setOnClickListener {
                //TODO this will be weird if there are a lot of templates.
                val templateList = TemplateList(manage.context).get()
                val adapter =
                    ManageCategoriesAdapter(
                        templateList,
                        json.getTemplate(position)
                    )
                val listDialog = ManageCategoryTemplatesDialog(
                    manage.context.getString(R.string.Manage),
                    manage.context.getString(R.string.Manage_Text, json.getName(position)),
                    manage.context,
                    adapter,
                    container
                )
                listDialog.setConfirm { _, _ ->
                    //generate clicked list
                    //json.setTemplate(position, adapter.localData)
                    json.setTemplate(position, adapter.getData())
                }
                listDialog.show()
            }
        }

        private fun bindRename(holder: ViewHolder, position: Int) {
            val rename = holder.itemView.findViewById<View>(R.id.rename)
            rename.setOnClickListener {
                val textBox =
                    TextBoxDialog(
                        rename.context.getString(R.string.Rename),
                        rename.context.getString(R.string.Rename_Text, json.getName(position)),
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

