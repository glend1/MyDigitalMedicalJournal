package com.mydigitalmedicaljournal.ui.templates.categories

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mydigitalmedicaljournal.R

class CategoryPagerAdapter(private val model: Array<CategoryModel>): RecyclerView.Adapter<CategoryPagerViewHolder>() {

    var text: String? = null
    var pager: ViewPager2? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPagerViewHolder {
        val view = model[viewType].layoutResId
        val layout = LayoutInflater.from(parent.context).inflate(view, parent, false)
        val holder = CategoryPagerViewHolder(layout)
        return holder
    }

    override fun getItemViewType(position: Int): Int = position

    override fun getItemCount() = model.size

    override fun onBindViewHolder(holder: CategoryPagerViewHolder, position: Int) {
        val item = holder.itemView
        if (position == 0 && text != null) {
            val textView = item.findViewById<TextView>(R.id.test_view)
            textView.text = text
            holder.itemView.setOnClickListener {
                pager?.currentItem = 1
            }
        } else if (position == 1) {
            item.findViewById<View>(R.id.back).setOnClickListener {
                pager?.currentItem = 0
            }
            item.findViewById<View>(R.id.rename).setOnClickListener {
                //TODO set action
                Log.i("RENAME", "button pressed")
            }
            item.findViewById<View>(R.id.manage).setOnClickListener {
                //TODO set action
                Log.i("MANAGE", "button pressed")
            }
            item.findViewById<View>(R.id.delete).setOnClickListener {
                //TODO set action
                Log.i("DELETE", "button pressed")
            }
        }
    }
}

class CategoryPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)