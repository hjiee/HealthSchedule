package com.example.healthschedule.view.main

import android.content.Context
import android.view.animation.Animation
import com.example.healthschedule.adapter.page.PageAdapterContract
import com.example.healthschedule.adapter.workout.WorkoutAdapterContract
import com.example.healthschedule.data.WeeklyWorkoutRepository

interface MainContract {

    interface Presenter {
        var mainView : View

        var pageAdapterAdapterView : PageAdapterContract.View

        var pagerAdapterModel : PageAdapterContract.Model

        var workoutAdapterView : WorkoutAdapterContract.AdaterView

        var workoutAdapterModel : WorkoutAdapterContract.AdapterModel

        var weeklyWorkoutData : WeeklyWorkoutRepository


        fun anim()

        fun animOn()

        fun animOff()

        fun getViewPagerMargin(context: Context) : Int

//        fun registrationWorkout(workoutName: ArrayList<CardItem>)

//        fun initWeekly() : ArrayList<CardItem>

//        fun loadItems(position : Int,isClear : Boolean)

        fun getItem(postion : Int) : String?
    }

    interface View {
        /**
         * Floating button을 클릭시 확장되어 있는 SubFloating button을 show/hide 한다.
         */
        fun actionFab(fabAction : Animation, fabRotateAction: Animation)

        /**
         * Floating button이 확장되어있는지 여부를 반환한다.
         */
        fun isExpanded(state : Boolean)
    }
}