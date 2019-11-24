package com.mydigitalmedicaljournal.ui.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mydigitalmedicaljournal.R

class InstructionsFragment : Fragment() {

    private lateinit var instructionsViewModel: InstructionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        instructionsViewModel =
            ViewModelProviders.of(this).get(InstructionsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_instructions, container, false)
        val textView: TextView = root.findViewById(R.id.text_instructions)
        instructionsViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}