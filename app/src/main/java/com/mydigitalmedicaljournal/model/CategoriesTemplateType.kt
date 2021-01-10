package com.mydigitalmedicaljournal.model

import com.mydigitalmedicaljournal.R

enum class CategoriesTemplateType(val layout: Int) {
    CATEGORY(R.layout.group_item),
    TEMPLATE(R.layout.list_item_child),
    OTHER(R.layout.empty_recycler)
}