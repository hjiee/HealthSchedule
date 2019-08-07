package com.example.healthschedule.adapter.workout

import com.example.healthschedule.data.dto.CardItem

interface WorkoutAdapterContract {
    interface AdaterView {
        fun notifyAdapter()
    }
    interface AdapterModel {
        fun registrationWorkout(name : ArrayList<CardItem>)

        fun getItem(position : Int) : String?

        fun getAll() : ArrayList<CardItem>?

        fun clearItem()
    }
}