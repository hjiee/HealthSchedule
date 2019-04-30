package com.example.healthschedule.data.source

import com.example.healthschedule.data.CardItem

object WeeklyWorkoutDataSource : WeeklyWorkoutSource {
    private var list = ArrayList<CardItem>()
    override fun getWorkout(position: Int, loadWorkoutCallback: WeeklyWorkoutSource.LoadCallback?) {
        if(!list.isNullOrEmpty()) {

        }
        loadWorkoutCallback?.onLoad(list)
    }

    override fun getWorkoutAll(loadWorkoutCallback: WeeklyWorkoutSource.LoadCallback?) {

        loadWorkoutCallback?.onLoad(list)
    }

    override fun addWorkout(arrayItem: ArrayList<CardItem>, loadWorkoutCallback: WeeklyWorkoutSource.LoadCallback?) {
        if (list.isNullOrEmpty()) {
            list = arrayItem
        }
        loadWorkoutCallback?.onLoad(list)
    }
}