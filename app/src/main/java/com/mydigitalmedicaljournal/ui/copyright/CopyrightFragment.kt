package com.mydigitalmedicaljournal.ui.copyright

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mydigitalmedicaljournal.R

class CopyrightFragment : Fragment() {

    private lateinit var copyrightViewModel: CopyrightViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO complete this fragment
        copyrightViewModel =
            ViewModelProviders.of(this).get(CopyrightViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_copyright, container, false)
        val textView: TextView = root.findViewById(R.id.text_copyright)
        copyrightViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}