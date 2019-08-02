package com.example.healthschedule.adapter.registration

import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthschedule.view.registration.dto.EachWorkoutDto
import kotlinx.android.synthetic.main.expansion_panel_sample_panel.view.*

class RegistrationViewHolder(view : View) : RecyclerView.ViewHolder(view) {


    fun bind(item : EachWorkoutDto) {
        itemView.sp_name.setSelection(item.selectedTitlePosition)
        itemView.tv_workout_name.text = item.name


        itemView.sp_name.onItemSelectedListener = (object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                item.selectedTitlePosition = position
                item.title = itemView.sp_name.selectedItem.toString()
            }
        })

//        itemView.headerIndicator.setOnClickListener { Toast.makeText(itemView.context,"",Toast.LENGTH_SHORT).show() }
    }
}