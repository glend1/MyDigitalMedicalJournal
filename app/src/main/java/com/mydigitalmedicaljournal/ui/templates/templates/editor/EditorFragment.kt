package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.CustomDivider
import com.mydigitalmedicaljournal.ui._generics.dialogs.OptionDialog

class EditorFragment : Fragment() {

    private lateinit var adapter: EditorAdapter
    private lateinit var templateManager: TemplateManager
    lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //TODO this should mostly only effect the template file
        setTemplate()
        root = inflater.inflate(R.layout.fragment_editor, container, false)
        initRecycler()
        setupSaveButton()
        setupDeleteButton()
        setupAddButton()
        return root
    }

    private fun setupAddButton() {
        root.findViewById<View>(R.id.add).setOnClickListener {
            val selectType =
                OptionDialog(
                    requireContext().getString(R.string.add_field),
                    requireContext().getString(R.string.add_field_text),
                    TemplateEnum.namedString,
                    requireContext()
                )
            selectType.setConfirm(DialogInterface.OnClickListener { _, _ ->
                val template = TemplateEnum.nameList[selectType.getSelected()]!!.createData()
                //TODO this adds a generic type but i need to add specific data, i think
                adapter.localData.data.add(template)
                adapter.notifyDataSetChanged()
            })
            selectType.show()
        }
    }

    private fun setupDeleteButton() {
        val v = root.findViewById<View>(R.id.delete)
        v.setOnClickListener {
            //TODO set this action
            navigateUp()
        }
    }

    private fun setupSaveButton() {
        root.findViewById<View>(R.id.save).setOnClickListener {
            val data = adapter.localData
            templateManager =
                TemplateManager(
                    data.id.toString(),
                    requireContext()
                )
            val validData = templateManager.setData(data)
            if (validData.test()) {
                navigateUp()
            } else {
                Snackbar.make(root, getString(R.string.error_message), Snackbar.LENGTH_LONG).show()
                for (i in 0 until adapter.itemCount) {
                    val test = adapter.getItemId(i)
                    Log.i("TEST", test.toString())
                }
                adapter.notifyDataSetChanged()
                validData.getErrors().forEach {
                    when(it) {
                        "name" -> {

                            Log.i("NAME", "name failed")
                        }
                        "date" -> {
                            Log.i("DATE", "date failed")
                        }
                    }
                }
            }
        }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun initRecycler() {
        val recycler = root.findViewById<RecyclerView>(R.id.template)
        val itemDecoration = CustomDivider(requireContext())
        recycler.addItemDecoration(itemDecoration)
        //TODO need some logic to get a TemplateFormat to pass it in
        /*
        templateData.template
            is the uuid which will be the filename for the json data
        open <uuid>.json convert it to a object or create an empty one
         */
        adapter = EditorAdapter(templateManager.getData())
        recycler.adapter = adapter
    }

    private fun setTemplate() {
        val extra = arguments?.get("data")
        templateManager = if (extra != null) {
            TemplateManager(
                extra.toString(),
                requireContext()
            )
        } else {
            TemplateManager()
        }
    }
}