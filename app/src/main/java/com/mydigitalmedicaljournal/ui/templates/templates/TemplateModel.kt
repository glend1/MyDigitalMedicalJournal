package com.mydigitalmedicaljournal.ui.templates.templates

import com.mydigitalmedicaljournal.R

enum class TemplateModel(val layoutResId: Int) {
    TEXT(R.layout.list_item_pager),
    BUTTONS(R.layout.template_button_pager);
}