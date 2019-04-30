package com.example.healthschedule.data.source

import com.example.healthschedule.data.CardItem

object WeeklyWorkoutRepository : WeeklyWorkoutSource {
    private val weeklyWorkoutDataSource = WeeklyWorkoutDataSource
    override fun getWorkout(position: Int, loadWorkoutCallback: WeeklyWorkoutSource.LoadCallback?) {
        weeklyWorkoutDataSource.getWorkout(position,object : WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                loadWorkoutCallback?.onLoad(list)
            }
        })
    }

    override fun getWorkoutAll(loadWorkoutCallback: WeeklyWorkoutSource.LoadCallback?) {
        weeklyWorkoutDataSource.getWorkoutAll(object : WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                loadWorkoutCallback?.onLoad(list)
            }
        })
    }

    override fun addWorkout(arrayItem: ArrayList<CardItem>, loadWorkoutCallback: WeeklyWorkoutSource.LoadCallback?) {
        weeklyWorkoutDataSource.addWorkout(arrayItem, object : WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                loadWorkoutCallback?.onLoad(list)
            }
        })
    }
}