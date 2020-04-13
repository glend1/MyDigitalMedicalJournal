package com.mydigitalmedicaljournal.ui.journal

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
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
        //TODO complete this fragment
        journalViewModel =
            ViewModelProviders.of(this).get(JournalViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_journal, container, false)
        val textView: TextView = root.findViewById(R.id.text_journal)
        journalViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        root.findViewById<Button>(R.id.button).setOnClickListener { v: View ->
            Log.i("FABBEFORE", v.isFocused.toString())
            v.isFocusable = true
            v.isFocusableInTouchMode = true ///add this line
            v.requestFocus()
            Log.i("FABAFTER", v.isFocused.toString())
        }
        root.findViewById<EditText>(R.id.editText2).setOnFocusChangeListener { v:View, hasFocus:Boolean ->
            if (!hasFocus) {
                val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }
        }
        return root
    }
}

// TODO add functionality for TAB
/*val fab: FloatingActionButton = findViewById(R.id.fab)
fab.setOnClickListener { view ->
    SnackBar.make(view, "Replace with your own action", SnackBar.LENGTH_LONG)
        .setAction("Action", null).show()
}*/