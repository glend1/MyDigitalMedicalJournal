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

    private lateinit var journalViewModel: JournalViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        journalViewModel =
            ViewModelProviders.of(this).get(JournalViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_journal, container, false)
        val textView: TextView = root.findViewById(R.id.text_journal)
        journalViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}

// TODO add functionality for TAB
/*val fab: FloatingActionButton = findViewById(R.id.fab)
fab.setOnClickListener { view ->
    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show()
}*/