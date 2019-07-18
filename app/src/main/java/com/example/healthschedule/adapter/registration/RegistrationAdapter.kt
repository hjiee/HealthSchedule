package com.example.healthschedule.adapter.registration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthschedule.R

class RegistrationAdapter<T>(private val list : MutableList<T>) : RecyclerView.Adapter<RegistrationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistrationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expansion_panel_sample_panel,parent,false)
        return RegistrationViewHolder(view)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: RegistrationViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun getItem() : MutableList<T>{
        return list
    }

    fun addItem(item : T) {
        list.add(0,item)
        notifyDataSetChanged()
        if(itemCount < 5) {
        }
    }

    fun removeAt(position : Int) {
        list.removeAt(position)
        notifyDataSetChanged()
    }

    fun removeAll() {
        list.clear()
        notifyDataSetChanged()
    }


}