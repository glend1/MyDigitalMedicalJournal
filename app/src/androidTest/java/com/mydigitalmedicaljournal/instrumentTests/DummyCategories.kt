package com.mydigitalmedicaljournal.instrumentTests

import com.mydigitalmedicaljournal.json.FileHelper
import com.mydigitalmedicaljournal.model.Categories

class DummyCategories(private val fileName: String) {
    companion object {
        const val FILENAME = "testCategories.json"
    }
    private val file = FileHelper(fileName, Utils.CONTEXT)

    fun get(): Categories {
        //TODO this could be better
        val fileContent = "[{\"id\":\"c71a34bd-8e95-4394-b0d6-5357c94c2250\",\"name\":\"apple\",\"templates\":[]},{\"id\":\"f5c4da32-8b2c-4669-a4b4-235594d49afc\",\"name\":\"cat\",\"templates\":[\"68aa63ff-1e34-49fd-afbd-bffecf95685c\",\"b132f1ab-d50b-4f84-a87e-bfbcadf91281\"]},{\"id\":\"7838caf6-2746-42ba-9be7-7d7b072ad840\",\"name\":\"dog\",\"templates\":[]}]"
        file.write(fileContent)
        return Categories(Utils.CONTEXT, fileName)
    }

    fun delete() {
        file.delete()
    }
}