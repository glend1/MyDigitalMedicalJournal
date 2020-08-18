package com.mydigitalmedicaljournal.ui.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mydigitalmedicaljournal.R

class SocialFragment : Fragment() {

    private lateinit var socialViewModel: SocialViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO complete this fragment
        socialViewModel =
            ViewModelProviders.of(this).get(SocialViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_social, container, false)
        val textView: TextView = root.findViewById(R.id.text_social)
        socialViewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })
        return root
    }
}