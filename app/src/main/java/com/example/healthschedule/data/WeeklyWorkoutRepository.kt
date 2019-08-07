package com.example.healthschedule.data

import com.example.healthschedule.data.dto.CardItem
import com.example.healthschedule.data.source.WeeklyWorkoutDataSource
import com.example.healthschedule.data.source.WeeklyWorkoutSource

class WeeklyWorkoutRepository : WeeklyWorkoutSource {
    private val weeklyWorkoutDataSource = WeeklyWorkoutDataSource()
    override fun getWorkout(position: Int, loadWorkoutCallback: WeeklyWorkoutSource.LoadCallback?) {
        weeklyWorkoutDataSource.getWorkout(position,object :
            WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                loadWorkoutCallback?.onLoad(list)
            }
        })
    }

    override fun getWorkoutAll(loadWorkoutCallback: WeeklyWorkoutSource.LoadCallback?) {
        weeklyWorkoutDataSource.getWorkoutAll(object :
            WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                loadWorkoutCallback?.onLoad(list)
            }
        })
    }

    override fun addWorkout(arrayItem: ArrayList<CardItem>, loadWorkoutCallback: WeeklyWorkoutSource.LoadCallback?) {
        weeklyWorkoutDataSource.addWorkout(arrayItem, object :
            WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                loadWorkoutCallback?.onLoad(list)
            }
        })
    }
}