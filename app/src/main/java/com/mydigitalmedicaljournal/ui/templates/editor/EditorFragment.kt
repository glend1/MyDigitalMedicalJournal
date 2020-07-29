package com.mydigitalmedicaljournal.ui.templates.editor

import android.content.DialogInterface
import android.os.Bundle
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
import com.mydigitalmedicaljournal.ui._generics.dialogs.ConfirmDialog
import com.mydigitalmedicaljournal.ui._generics.dialogs.OptionDialog
import java.util.*

class EditorFragment : Fragment() {

    private lateinit var adapter: EditorAdapter
    private lateinit var templateManager: TemplateManager
    lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
                adapter.add(template)
            })
            selectType.show()
        }
    }

    private fun setupDeleteButton() {
        val v = root.findViewById<View>(R.id.delete)
        v.setOnClickListener {
            if (templateManager.fileExists()) {
                val alert =
                    ConfirmDialog(
                        v.context.getString(R.string.Confirm_Template, templateManager.getName()),
                        v.context.getString(R.string.Confirm_Template_Warning),
                        v.context
                    )
                alert.setConfirm(DialogInterface.OnClickListener { _, _ ->
                    templateManager.delete()
                    navigateUp()
                })
                alert.show()
            } else {
                Snackbar.make(root, getString(R.string.file_not_found), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupSaveButton() {
        //TODO cannot save files with "data fields" opening them breaks the code
        //TODO validate the data before this step
        root.findViewById<View>(R.id.save).setOnClickListener {
            val data = adapter.localData
            val validData = templateManager.setData(data)
            if (validData.test()) {
                navigateUp()
            } else {
                Snackbar.make(root, getString(R.string.error_message), Snackbar.LENGTH_LONG).show()
                adapter.validate(validData)
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
        adapter = EditorAdapter(
            templateManager.getData()
        )
        recycler.adapter = adapter
    }

    private fun setTemplate() {
        val extra = arguments?.get("data")
        templateManager = if (extra != null) {
            TemplateManager(
                requireContext(),
                UUID.fromString(extra.toString())
            )
        } else {
            TemplateManager(requireContext())
        }
    }
}