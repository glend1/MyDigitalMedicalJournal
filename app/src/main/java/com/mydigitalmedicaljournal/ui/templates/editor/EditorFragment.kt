package com.mydigitalmedicaljournal.ui.templates.editor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.TemplateEnum
import com.mydigitalmedicaljournal.template.fields.data.DataName
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.CustomDivider
import com.mydigitalmedicaljournal.ui._generics.dialogs.ConfirmDialog
import com.mydigitalmedicaljournal.ui._generics.dialogs.ListDialog
import java.util.*

class EditorFragment : Fragment() {

    private lateinit var adapter: EditorAdapter
    private lateinit var templateManager: TemplateManager
    lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setTemplate()
        root = inflater.inflate(R.layout.fragment_editor, container, false)
        initRecycler()
        setupSaveButton()
        setupDeleteButton()
        setupAddButton(container)
        return root
    }

    private fun setupAddButton(container: ViewGroup?) {
        root.findViewById<View>(R.id.add).setOnClickListener {
            val selectType =
                ListDialog(
                    requireContext().getString(R.string.add_field),
                    requireContext().getString(R.string.add_field_text),
                    TemplateEnum.namedString,
                    requireContext(),
                    container
                )
            selectType.setListener(fun (pos: Int) {
                val bundle = bundleOf("type" to TemplateEnum.nameList[pos], "filename" to templateManager.getId())
                root.findNavController().navigate(R.id.fieldEditorFragment, bundle)
                selectType.dismiss()
            })
            selectType.show()
        }
    }

    private fun setupDeleteButton() {
        val v = root.findViewById<View>(R.id.delete_template)
        v.setOnClickListener {
            if (templateManager.fileExists()) {
                val alert =
                    ConfirmDialog(
                        v.context.getString(R.string.Confirm_Template, (templateManager.getData().getData(0) as DataName).name),
                        v.context.getString(R.string.Confirm_Template_Warning),
                        v.context
                    )
                alert.setConfirm { _, _ ->
                    templateManager.delete()
                    navigateUp()
                }
                alert.show()
            } else {
                Snackbar.make(root, getString(R.string.file_not_found), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupSaveButton() {
        root.findViewById<View>(R.id.save).setOnClickListener {
            val data = adapter.templateManager.getData()
            val validData = templateManager.setData(data)
            if (validData.isEmpty()) {
                navigateUp()
            } else {
                Snackbar.make(root, getString(R.string.error_message), Snackbar.LENGTH_LONG).show()
                //adapter.validate(validData)
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
            templateManager
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

    override fun onPause() {
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
        super.onPause()
    }
}