package com.example.healthschedule.view.registration

import androidx.fragment.app.FragmentManager
import com.example.healthschedule.data.CardItem
import com.example.healthschedule.data.source.WeeklyWorkoutRepository

interface RegistrationContract {

    interface View {

        fun setDailyWorkout(viewId : Int, result : String)
    }

    interface Presenter {
        var weeklyWorkoutData: WeeklyWorkoutRepository

        fun registrationWorkout(workoutName: ArrayList<CardItem>)

        fun initWeekly() : ArrayList<CardItem>

        fun addWeekly() : ArrayList<CardItem>

        fun dialog(supportFragmentManager: FragmentManager,view : android.view.View)
    }
}