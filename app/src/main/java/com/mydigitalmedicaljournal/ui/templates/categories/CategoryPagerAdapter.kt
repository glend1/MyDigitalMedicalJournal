package com.mydigitalmedicaljournal.ui.templates.categories

import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mydigitalmedicaljournal.R
import com.mydigitalmedicaljournal.dialog.Confirm
import com.mydigitalmedicaljournal.dialog.TextBox

class CategoryPagerAdapter(private val model: Array<CategoryModel>, private val parent: CategoryRecyclerAdapter): RecyclerView.Adapter<CategoryPagerViewHolder>() {

    var thisItem: Int = 0
    var text: String? = null
    var pager: ViewPager2? = null
    private var rename: TextBox? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryPagerViewHolder {
        val view = model[viewType].layoutResId
        val layout = LayoutInflater.from(parent.context).inflate(view, parent, false)
        return CategoryPagerViewHolder(layout)
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
                /*val listener = DialogInterface.OnClickListener { _, _ ->
                    parent.cat.data[thisItem].name = rename!!.getText()
                    parent.cat.sort()
                    parent.cat.save()
                    parent.notifyDataSetChanged()
                }
                rename = TextBox(
                    "Rename",
                    "Please type the new name for \"${parent.cat.data[thisItem].name}\"",
                    parent.cat.data[thisItem].name,
                    listener,
                    parent.pager.context
                )
                parent.notifyDataSetChanged()
                rename?.show()*/
            }
            item.findViewById<View>(R.id.manage).setOnClickListener {
                //TODO set action manage categories
                Log.i("MANAGE", "button pressed")
                parent.notifyDataSetChanged()
            }
            item.findViewById<View>(R.id.delete).setOnClickListener {
                /*val listener = DialogInterface.OnClickListener { _, _ ->
                    parent.cat.data.removeAt(thisItem)
                    parent.cat.save()
                    parent.notifyDataSetChanged()
                }
                val alert = Confirm(
                    "Are you sure you want to delete Category named \"${parent.cat.data[thisItem].name}\"?",
                    "You will not loose any data or any saved templates. This action cannot be undone.",
                    listener,
                    parent.pager.context
                )
                parent.notifyDataSetChanged()
                alert.show()*/
            }
        }
    }
}

class CategoryPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)