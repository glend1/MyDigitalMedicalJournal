package com.mydigitalmedicaljournal.instrumentTests.file

import com.mydigitalmedicaljournal.instrumentTests.DummyCategories
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplateFile
import com.mydigitalmedicaljournal.instrumentTests.DummyTemplates
import com.mydigitalmedicaljournal.instrumentTests.Utils
import com.mydigitalmedicaljournal.model.Categories
import com.mydigitalmedicaljournal.model.CategoriesAndTemplatesList
import com.mydigitalmedicaljournal.template.file.TemplateList
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TemplatesAndCategories {
    private lateinit var data: List<TemplateList.FileList>
    private val dt = DummyTemplates()
    private val dc = DummyCategories()
    private lateinit var templateList: TemplateList
    private lateinit var categoryList: Categories
    private lateinit var ct: CategoriesAndTemplatesList

    @Before
    fun setup() {
        templateList = dt.get()
        data = templateList.get()
        categoryList = dc.get()
        categoryList.setTemplate(0, mutableListOf(data[1].id))
        categoryList.setTemplate(1, mutableListOf(data[0].id, data[2].id))
        categoryList.setTemplate(2, mutableListOf(data[1].id))
        ct = CategoriesAndTemplatesList(Utils.CONTEXT, DummyTemplateFile.directory, DummyCategories.FILENAME)
    }

    @After
    fun teardown() {
        dt.delete()
        dc.delete()
    }

    @Test
    fun getFlat() {
        Assert.assertEquals(ct.getFlatList().size, 7)
    }
}