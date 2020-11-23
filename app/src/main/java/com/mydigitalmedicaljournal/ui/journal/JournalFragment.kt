package com.mydigitalmedicaljournal.ui.journal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mydigitalmedicaljournal.R

class JournalFragment : Fragment() {

    //TODO inputs should have a autofill for the type of input they are, category could attempt to match against categories you have already saved

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //TODO complete this fragment
        val root = inflater.inflate(R.layout.fragment_journal, container, false)
        setupFab(root)
        return root
    }

    private fun setupFab(view: View) {
        view.findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            findNavController().navigate(R.id.templateSelector)
        }
    }
}

