package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.editor.TemplateView
import com.mydigitalmedicaljournal.template.editor.TemplateView.Companion.getStringList
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.CustomDivider
import com.mydigitalmedicaljournal.ui._generics.dialogs.OptionDialog

class EditorFragment : Fragment() {

    //private lateinit var recycler: RecyclerView
    //private lateinit var templateName: Template.Template
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
                    context!!.getString(R.string.New),
                    context!!.getString(R.string.New_Text),
                    getStringList(
                        context!!
                    ),
                    context!!
                )
            selectType.setListener(DialogInterface.OnClickListener { _, _ ->
                //TODO this is too easily broken
                val template = TemplateView.getView(selectType.getSelected() + 2)!!.getObj()
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
            /*Log.i("DELETE", "Delete clicked")
            Log.i("FABBEFORE", v.isFocused.toString())
            v.isFocusable = true
            v.isFocusableInTouchMode = true ///add this line
            v.requestFocus()
            Log.i("FABAFTER", v.isFocused.toString())*/
            navigateUp()
        }
    }

    private fun setupSaveButton() {
        root.findViewById<View>(R.id.save).setOnClickListener {
            templateManager = TemplateManager(adapter.localData.id.toString(), context!!)
            templateManager.setData(adapter.localData)
            navigateUp()
        }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun initRecycler() {
        val recycler = root.findViewById<RecyclerView>(R.id.template)
        val itemDecoration = CustomDivider(context!!)
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
        if (extra != null) {
            templateManager = TemplateManager(extra.toString(), context!!)
        } else {
            //TODO need to set an empty manager to be used if needed
        }
    }
}