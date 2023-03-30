package com.dennis.porterapi.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.BaseAdapter
import android.widget.TextView
import com.dennis.porterapi.R
import com.dennis.porterapi.data.Characters

class MyListAdapter(private val data:ArrayList<Characters>)
    :BaseAdapter(){
    override fun getCount(): Int {
        return data.size
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.item,parent,false)
        val itemView: TextView = view.findViewById(R.id.txt_name_house)
        itemView.text = data[position].toString()
        return view
    }


}