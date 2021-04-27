package com.mydigitalmedicaljournal.ui.templates.dialog

import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.template.fields.data.DataName
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

    private fun setName() {
        templateManager.getName().name = castInput.dialogName.text.toString()
    }

    private fun setDate() {
        templateManager.getDate().time = DataTime.TimeFormat.getType(castInput.dialogDate.checkedRadioButtonId)
    }

    fun save(): Boolean {
        setName()
        setDate()
        templateManager.setData()
        val errors = templateManager.getData().validate()
        if (errors.isEmpty()) {
            return true
        } else {
            if (!errors.containsKey(DataName.NAME_FIELD)) {
                castInput.errorName(GONE, null)
            }
            if (!errors.containsKey(DataTime.TIME_FIELD)) {
                castInput.errorDate(GONE, null)
            }
            errors.forEach { (k, v) ->
                when (k) {
                    DataName.NAME_FIELD -> {
                        castInput.errorName(VISIBLE, v)
                    }
                    DataTime.TIME_FIELD -> {
                        castInput.errorDate(VISIBLE, v)
                    }
                }
            }
            Snackbar.make(castInput.view, R.string.error_message, Snackbar.LENGTH_LONG).show()
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