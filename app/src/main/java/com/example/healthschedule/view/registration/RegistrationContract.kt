package com.example.healthschedule.view.registration

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.example.healthschedule.base.BaseContract
import com.example.healthschedule.data.dto.CardItem
import com.example.healthschedule.data.WeeklyWorkoutRepository

interface RegistrationContract {

    interface View : BaseContract.View {

        /**
         * 결과를 반환한다.
         */
        fun returnToResult()
        /**
         * 요일별로 운동할 목록들을 선택한다.
         */
        fun setDailyWorkout(viewId : Int, result : String)
    }

    interface Presenter {
        var weeklyWorkoutData: WeeklyWorkoutRepository

        /**
         * 선택된 운동목록들을 데이터베이스에 저장한다.
         */
        fun registrationWorkout(workoutName: ArrayList<CardItem>)

//        fun initWeekly() : ArrayList<CardItem>

//        fun addWeekly() : ArrayList<CardItem>

        fun dialog(supportFragmentManager: FragmentManager,view : android.view.View)

        fun showAlterDialog(alterDialog: AlertDialog.Builder)
    }
}