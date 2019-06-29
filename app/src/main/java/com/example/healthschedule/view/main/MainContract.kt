package com.example.healthschedule.view.main

import android.content.Context
import android.view.animation.Animation
import com.example.healthschedule.adapter.page.PageAdapterContract
import com.example.healthschedule.adapter.workout.WorkoutAdapterContract
import com.example.healthschedule.data.CardItem
import com.example.healthschedule.data.source.WeeklyWorkoutRepository

interface MainContract {

    interface Presenter {
        var mainView : View

        var pageAdapterAdapterView : PageAdapterContract.View

        var pagerAdapterModel : PageAdapterContract.Model

        var workoutAdapterView : WorkoutAdapterContract.AdaterView

        var workoutAdapterModel : WorkoutAdapterContract.AdapterModel

        var weeklyWorkoutData : WeeklyWorkoutRepository

        fun anim()

        fun getViewPagerMargin(context: Context) : Int

        fun registrationWorkout(workoutName: ArrayList<CardItem>)

        fun initWeekly() : ArrayList<CardItem>

        fun loadItems(position : Int,isClear : Boolean)

        fun getItem(postion : Int) : String?
    }
    interface View {
        fun showSubFab(fabAction : Animation, fabRotateAction : Animation)
        fun hideSubFab(fabAction : Animation, fabRotateAction : Animation)
        fun isClickable(state : Boolean)
    }
}