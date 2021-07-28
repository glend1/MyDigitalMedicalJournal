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
    protected val errorTextViews = mutableMapOf<Int, TextView>()
    private val saveButton: ConstraintLayout = view.findViewById(R.id.save)
    protected fun setSaveListener(listener: View.OnClickListener) {
        saveButton.setOnClickListener(listener)
    }

    fun validate(data: GenericData) {
        val errorRes = data.validate()
        val errorNum = data.errorCount(errorRes)
        if (errorNum == 0) {
            template.save()
            view.findNavController().navigateUp()
        } else {
            showErrors(errorRes)
            Snackbar.make(view, toastMessage(errorNum), Snackbar.LENGTH_LONG).show()
        }
    }

    private fun showErrors(errorRes : MutableMap<Int, String?>) {
        errorRes.forEach{ (id, i) ->
            val textView = errorTextViews[id]
            if (textView != null) {
                if (i == null) {
                    textView.visibility = View.GONE
                } else {
                    textView.visibility = View.VISIBLE
                    textView.text = i
                }
            }
        }
    }

    private fun toastMessage(errorsNum : Int): String {
        return view.context.getString(R.string.number_errors, errorsNum.toString())
    }
}