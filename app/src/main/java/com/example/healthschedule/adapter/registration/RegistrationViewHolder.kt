package com.example.healthschedule.adapter.registration

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.expansion_panel_sample_panel.view.*

class RegistrationViewHolder(view : View) : RecyclerView.ViewHolder(view) {


    fun <T> bind(item : T) {
        itemView.tv_workout_name.text = item.toString()

//        itemView.headerIndicator.setOnClickListener { Toast.makeText(itemView.context,"",Toast.LENGTH_SHORT).show() }
    }
}