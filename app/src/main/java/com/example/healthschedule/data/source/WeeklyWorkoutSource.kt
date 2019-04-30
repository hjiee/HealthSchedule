package com.example.healthschedule.data.source

import com.example.healthschedule.data.CardItem

interface WeeklyWorkoutSource {

    interface LoadCallback {
        fun onLoad(list : ArrayList<CardItem>)
    }

    fun getWorkout(position : Int , loadWorkoutCallback : LoadCallback?)

    fun getWorkoutAll(loadWorkoutCallback : LoadCallback?)

    fun addWorkout(arrayItem : ArrayList<CardItem>, loadWorkoutCallback : LoadCallback?)
}