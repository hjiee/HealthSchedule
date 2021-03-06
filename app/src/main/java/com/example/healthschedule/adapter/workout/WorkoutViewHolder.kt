package com.example.healthschedule.adapter.workout

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.healthschedule.R

class WorkoutViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_weekly_workout_item, parent, false)){

    val tvWorkoutName by lazy {
        itemView.findViewById(R.id.tv_workout_name) as TextView
    }

}