package com.vietquoc.clv.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.vietquoc.clv.R
import com.vietquoc.clv.model.Student

class StudentAdapter(
    private var activity: Activity,
    private var layout: Int,
    private var dsStudent: List<Student>
) : BaseAdapter() {
    override fun getCount(): Int {
        return dsStudent.size
    }

    override fun getItem(position: Int): Any {
        return dsStudent.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder", "SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewHolder: ViewHolder
        val layoutInflater = activity.layoutInflater
        val view = layoutInflater.inflate(layout, parent, false)
        if (convertView == null) {
            viewHolder = ViewHolder(view)
            viewHolder.tvIdStudent = view!!.findViewById<TextView>(R.id.tvIdStudent)
            viewHolder.tvNameStudent =
                view.findViewById<TextView>(R.id.tvNameStudent)
            view.tag = viewHolder
        } else {
            viewHolder = view!!.tag as ViewHolder
        }
        viewHolder.tvIdStudent!!.text = dsStudent[position].id.toString()
        viewHolder.tvNameStudent!!.text = dsStudent[position].name
        return view
    }

    class ViewHolder(view: View) {
        var tvIdStudent = view.findViewById<TextView>(R.id.tvIdStudent)
        var tvNameStudent = view.findViewById<TextView>(R.id.lvStudent)
    }
}