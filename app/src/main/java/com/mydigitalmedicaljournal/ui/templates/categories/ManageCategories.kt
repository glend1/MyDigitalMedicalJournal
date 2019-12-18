package com.mydigitalmedicaljournal.ui.templates.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.mydigitalmedicaljournal.R


class ManageCategories : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //TODO make json for categories

        class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
            val textView = v.findViewById<TextView>(R.id.text_item)

            inner class CategoryPagerAdapter: PagerAdapter() {
                override fun isViewFromObject(view: View, `object`: Any): Boolean {
                    return view === `object`
                }

                override fun getCount() = 2

            }

            init {
                val vp = v.findViewById<ViewPager>(R.id.cagegory_pager)
                val pagerAdapter = CategoryPagerAdapter()
                vp.adapter = pagerAdapter
            }

        }

        // Provide a reference to the views for each data item
        class CategoryListAdapter(private val myDataset: Array<String>) :
            RecyclerView.Adapter<ViewHolder>() {
            // Complex data items may need more than one view per item, and
            // you provide access to all the views for a data item in a view holder.
            // Each data item is just a string in this case that is shown in a TextView.

            // Create new views (invoked by the layout manager)
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): ViewHolder {
                // create a new view
                val layout = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_category, parent, false)

                // set the view's size, margins, paddings and layout parameters
                return ViewHolder(layout)
            }

            // Replace the contents of a view (invoked by the layout manager)
            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                // - get element from your dataset at this position
                // - replace the contents of the view with that element
                holder.itemView.setOnClickListener {
                    when (position) {
                        0 -> {
                            Log.d("TEXT", "backup")
                        }
                    }
                }
                holder.textView.text = myDataset[position]
            }

            override fun getItemCount() = myDataset.size

        }
        val root = inflater.inflate(R.layout.fragment_manage_categories, container, false)
        val array = arrayOf("this", "is", "a", "test")
        val viewAdapter = CategoryListAdapter(array)
        val templateList = root.findViewById<RecyclerView>(R.id.template_manage_category)
        val itemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        templateList.addItemDecoration(itemDecoration)
        templateList.adapter = viewAdapter

        return root

    }
}

