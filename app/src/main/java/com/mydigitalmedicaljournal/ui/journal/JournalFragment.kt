package com.mydigitalmedicaljournal.ui.journal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mydigitalmedicaljournal.R

class JournalFragment : Fragment() {

    private lateinit var homeViewModel: JournalViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(JournalViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_journal, container, false)
        val textView: TextView = root.findViewById(R.id.text_journal)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}