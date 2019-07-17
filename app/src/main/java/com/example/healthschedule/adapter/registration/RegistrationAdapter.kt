package com.example.healthschedule.adapter.registration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthschedule.R

class RegistrationAdapter<T>(private val list : ArrayList<T>) : RecyclerView.Adapter<RegistrationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistrationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expansion_panel_sample_panel,parent,false)
        return RegistrationViewHolder(view)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: RegistrationViewHolder, position: Int) {
        holder.bind(list[position])
    }
}