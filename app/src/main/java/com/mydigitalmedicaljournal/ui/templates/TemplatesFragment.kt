package com.mydigitalmedicaljournal.ui.templates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mydigitalmedicaljournal.R

class TemplatesFragment : Fragment() {

    private lateinit var templatesViewModel: TemplatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        templatesViewModel =
            ViewModelProviders.of(this).get(TemplatesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_templates, container, false)
        val textView: TextView = root.findViewById(R.id.text_templates)
        templatesViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}