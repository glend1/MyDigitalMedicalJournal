package com.mydigitalmedicaljournal.template.fields.editor

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.GenericData
import com.mydigitalmedicaljournal.template.file.TemplateManager

abstract class GenericEditor(protected val view: View, protected val template: TemplateManager, protected val position: Int?) {
    private val saveButton: ConstraintLayout = view.findViewById(R.id.save)
    protected fun setSaveListener(listener: View.OnClickListener) {
        saveButton.setOnClickListener(listener)
    }

    fun validate(data: GenericData) {
        val errorRes = data.validate()
        if (errorRes.isEmpty()) {
            template.setData()
            view.findNavController().navigateUp()
        } else {
            Snackbar.make(view, toastMessage(errorRes), Snackbar.LENGTH_LONG).show()
            showErrors(errorRes)
        }
    }

    protected fun showError(error: TextView, text: Int) {
        error.text = view.context.resources.getText(text)
        error.visibility = View.VISIBLE
    }

    abstract fun showErrors(errorRes : MutableMap<Int, Int>)


    private fun toastMessage(errorRes: MutableMap<Int, Int>): String {
        return view.context.getString(R.string.number_errors, errorRes.size.toString())
    }
}