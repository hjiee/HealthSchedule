package com.example.healthschedule.adapter.registration

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.healthschedule.view.registration.dto.WorkoutDto
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.expansion_panel_sample_panel.view.*
import java.util.*

class RegistrationViewHolder(view : View) : RecyclerView.ViewHolder(view) {


    fun bind(item : WorkoutDto) {
        itemView.sp_name.setSelection(item.selectedTitlePosition)
        itemView.tv_workout_name.text = item.name


        itemView.sp_name.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                item.selectedTitlePosition = position
            }
        })

//        itemView.headerIndicator.setOnClickListener { Toast.makeText(itemView.context,"",Toast.LENGTH_SHORT).show() }
    }
}