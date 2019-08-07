package com.example.healthschedule.adapter.workout

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.example.healthschedule.data.dto.CardItem

class WorkoutAdapter : RecyclerView.Adapter<WorkoutViewHolder>(),
    WorkoutAdapterContract.AdaterView,
    WorkoutAdapterContract.AdapterModel {

    private lateinit var arrayList : ArrayList<CardItem>
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): WorkoutViewHolder = WorkoutViewHolder(parent)

    override fun getItemCount(): Int = if(arrayList.size >0) arrayList.size else 0

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        arrayList[position].let {
            holder.tvWorkoutName.text = it.workoutName
        }
    }

    override fun clearItem() {
        arrayList?.clear()
    }

    override fun registrationWorkout(name: ArrayList<CardItem>) {
        arrayList = name
    }

    override fun getItem(position: Int): String? = if(!arrayList.isNullOrEmpty()) arrayList[position].workoutName else null

    override fun getAll(): ArrayList<CardItem>? = arrayList

    override fun notifyAdapter() {
        notifyDataSetChanged()
    }
}