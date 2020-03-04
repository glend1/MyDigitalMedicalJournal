package com.mydigitalmedicaljournal.ui.templates.templates.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.ui.support.SupportViewModel
import androidx.lifecycle.Observer

class EditorFragment : Fragment() {

    private lateinit var supportViewModel: SupportViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO complete this fragment
        supportViewModel =
            ViewModelProviders.of(this).get(SupportViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_support, container, false)
        val textView: TextView = root.findViewById(R.id.text_support)
        supportViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}