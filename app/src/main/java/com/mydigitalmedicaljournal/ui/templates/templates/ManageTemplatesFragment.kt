package com.mydigitalmedicaljournal.ui.templates.templates

import androidx.fragment.app.Fragment

//import com.mydigitalmedicaljournal.model.Templates


class ManageTemplatesFragment : Fragment() {

    //TODO this needs to show templates

    /*var add: TextBox? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_manage_templates, container, false)
        val templates = Templates(context!!)
        val viewAdapter = TemplateRecyclerAdapter(templates)
        val templateList = root.findViewById<RecyclerView>(R.id.template_manage_template)
        val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter
        root.findViewById<View>(R.id.add).setOnClickListener {
            val listener = DialogInterface.OnClickListener { dialog, which->
                viewAdapter.templates.data.add(Templates.Entry(add!!.getText()))
                viewAdapter.templates.save()
                viewAdapter.notifyDataSetChanged()
            }
            add = TextBox(
                "Add New Category",
                "Please type the name for the new category",
                "",
                listener,
                context!!
            )
            add?.show()
        }
        return root

    }*/
}

