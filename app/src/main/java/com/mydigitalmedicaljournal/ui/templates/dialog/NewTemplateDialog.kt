package com.mydigitalmedicaljournal.ui.templates.dialog

import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.DataTime
import com.mydigitalmedicaljournal.template.file.TemplateManager
import com.mydigitalmedicaljournal.ui._generics.dialogs.AbstractDialog
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.AbstractDialogCancel
import com.mydigitalmedicaljournal.ui._generics.dialogs.cancel.DialogCancelNull
import com.mydigitalmedicaljournal.ui._generics.dialogs.input.AbstractDialogInput
import java.util.*

class NewTemplateDialog(title: String, message: String, context: Context, container: ViewGroup?) : AbstractDialog(title, message, context) {

    override var cancel: AbstractDialogCancel = DialogCancelNull(builder, context)
    override var input: AbstractDialogInput = TemplateInput(builder, context, container)
    private val castInput = input as TemplateInput
    private val templateManager = TemplateManager(context)

    init {
        setConfirm(R.string.save) { _, _ -> }
    }

    private fun setName(): MutableMap<Int, String?> {
        templateManager.getName().name = castInput.dialogName.text.toString()
        return templateManager.getData().getName().validate()
    }

    private fun setDate(): MutableMap<Int, String?> {
        templateManager.getDate().time = DataTime.TimeFormat.getType(castInput.dialogDate.checkedRadioButtonId)
        return templateManager.getData().getTime().validate()
    }

    fun save(): Boolean {
        val validName = setName()
        val validTime = setDate()
        if (templateManager.setData()) {
            return true
        } else {
            var errors = 0
            var nameVis = GONE
            var timeVis = GONE
            if (validName[R.id.name_field] != null) {
                nameVis = VISIBLE
                errors++
            }
            if (validTime[R.id.time_field] != null) {
                timeVis = VISIBLE
                errors++
            }
            castInput.errorName(nameVis, validName[R.id.name_field])
            castInput.errorDate(timeVis, validTime[R.id.time_field])
            Snackbar.make(castInput.view, context.getString(R.string.number_errors, errors.toString()), Snackbar.LENGTH_LONG).show()
            return false
        }
    }

    fun disableAutoDismiss(listener: View.OnClickListener) {
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(listener)
    }

    fun getTemplateId(): UUID {
        return templateManager.getId()
    }

}