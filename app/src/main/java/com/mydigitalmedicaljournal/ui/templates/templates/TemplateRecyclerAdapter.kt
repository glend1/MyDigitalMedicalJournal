package com.mydigitalmedicaljournal.ui.templates.templates

//import com.mydigitalmedicaljournal.model.Templates

/*class TemplateRecyclerAdapter(val templates: Templates) : RecyclerView.Adapter<TemplateRecyclerViewHolder>() {

    lateinit var textView: TextView
    private lateinit var pagerAdapter: TemplatePagerAdapter
    lateinit var pager: ViewPager2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemplateRecyclerViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_pager, parent, false)
        //registerAdapterDataObserver(cat)
        return TemplateRecyclerViewHolder(layout)
    }

    override fun onBindViewHolder(holder: TemplateRecyclerViewHolder, position: Int) {
        pagerAdapter = TemplatePagerAdapter(TemplateModel.values(), this)
        pager = holder.itemView.findViewById(R.id.pager)
        pager.adapter = pagerAdapter
        pagerAdapter.text = templates.data[position].name
        pagerAdapter.pager = pager
        pagerAdapter.position = position
    }

    override fun getItemCount() = templates.data.size

}

class TemplateRecyclerViewHolder(v: View) : RecyclerView.ViewHolder(v)

*/