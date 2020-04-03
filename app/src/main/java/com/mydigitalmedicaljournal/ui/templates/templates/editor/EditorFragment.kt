package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.editor.TemplateView
import com.mydigitalmedicaljournal.template.editor.TemplateView.Companion.getStringList
import com.mydigitalmedicaljournal.template.file.TemplateDefinition
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.CustomDivider
import com.mydigitalmedicaljournal.ui._generics.OptionDialog

class EditorFragment : Fragment() {

    //private lateinit var templateName: Template.Template
    //private lateinit var templateDefinition: TemplateDefinition
    private lateinit var templateManager: TemplateManager
    //private lateinit var recycler: RecyclerView
    private lateinit var adapter: EditorAdapter
    lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //TODO this should mostly only effect the template file
        /*val templateFound = getTemplateId()
        getTemplate(templateFound)*/
        root = inflater.inflate(R.layout.fragment_editor, container, false)
        setTemplateText()
        initRecycler()
        setupSaveButton()
        setupDeleteButton()
        setupAddButton()
        return root
    }

    private fun setupAddButton() {
        root.findViewById<View>(R.id.add).setOnClickListener {
            val selectType = OptionDialog(
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
                //TODO this adds a generic type but i need to add specific data
                adapter.localData.data.add(template)
                adapter.notifyDataSetChanged()
            })
            selectType.show()
        }
    }

    private fun setupDeleteButton() {
        root.findViewById<View>(R.id.delete).setOnClickListener {
            //TODO set this action
            // this should delete the reference in categories json too
            // this should delete the reference in templates json too
            Log.i("DELETE", "Delete clicked")
        }
    }

    private fun setupSaveButton() {
        root.findViewById<View>(R.id.save).setOnClickListener {
            templateManager = TemplateManager(adapter.localData.id.toString(), context!!)
            templateManager.setData(adapter.localData)
            /*Log.i("SAVE", templateDefinition.toString())
            //TODO set this action
            // this should update the name in templates json too
            Log.i("SAVE", "Save clicked")
            templateDefinition = adapter.localData
            templateDefinition.save()*/
        }
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
        adapter = EditorAdapter()
        recycler.adapter = adapter
    }

    private fun setTemplateText() {
        val editText = root.findViewById<EditText>(R.id.editText)
        //editText.setText(templateName.name)
    }

    /*private fun getTemplateId(): Boolean {
        val extra = arguments?.get("data")
        /*for (data in Template(context!!).data) {
            if (extra == data.id) {
                templateName = data
                return true
            }
        }
        templateName = Template.Template("")*/
        return false
    }

    private fun getTemplate(templateFound: Boolean) {
        if (templateFound) {
            //TODO get json from this value
            //templateName.id
            templateDefinition
        } else {
            templateDefinition = TemplateDefinition()
        }
    }*/
}