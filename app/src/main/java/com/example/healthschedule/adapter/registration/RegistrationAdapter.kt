package com.example.healthschedule.adapter.registration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthschedule.R
import com.example.healthschedule.view.registration.dto.EachWorkoutDto

class RegistrationAdapter(private val list: MutableList<EachWorkoutDto>) : RecyclerView.Adapter<RegistrationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistrationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expansion_panel_sample_panel, parent, false)
        return RegistrationViewHolder(view)
    }

    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: RegistrationViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun getItem(): MutableList<EachWorkoutDto> {
        return list
    }

    fun updateItem(item: EachWorkoutDto) {

    }

    fun addItem(item: EachWorkoutDto) {
        if (itemCount < 5) {
            list.add(0, item)
            notifyDataSetChanged()
        }
    }

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyDataSetChanged()
    }

    fun removeAll() {
        list.clear()
        notifyDataSetChanged()
    }
}