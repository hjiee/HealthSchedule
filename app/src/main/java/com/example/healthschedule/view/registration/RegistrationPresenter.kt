package com.example.healthschedule.view.registration

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.healthschedule.R
import com.example.healthschedule.adapter.workout.WorkoutAdapterContract
import com.example.healthschedule.data.CardItem
import com.example.healthschedule.data.source.WeeklyWorkoutRepository
import com.example.healthschedule.data.source.WeeklyWorkoutSource

class RegistrationPresenter : RegistrationContract.Presenter {

    lateinit var view: RegistrationContract.View
    lateinit var workoutAdapterView: WorkoutAdapterContract.AdaterView
    lateinit var workoutAdapterModel: WorkoutAdapterContract.AdapterModel
    override lateinit var weeklyWorkoutData: WeeklyWorkoutRepository

    override fun registrationWorkout(workoutName: ArrayList<CardItem>) {
        weeklyWorkoutData.addWorkout(workoutName, object : WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                workoutAdapterModel.registrationWorkout(list)
                workoutAdapterView?.notifyAdapter()
            }
        })
    }

    override fun dialog(supportFragmentManager: FragmentManager, viewId: Int) {
        when (viewId) {
            R.id.btn_bring -> BringWorkoutDialogFragment.newInstance().show(supportFragmentManager,"")
            else -> {
                 RegistrationDialogFragment.newInstance().run {
                     show(supportFragmentManager, getTag(viewId))
                     setDailyWorkoutCallback(object : RegistrationDialogFragment.DailyWorkoutCallback {
                         override fun result() {
                             Log.e("Registration", "test")
                         }
                     })
                 }
            }
        }
    }



    override fun addWeekly(): ArrayList<CardItem> {
        var list: ArrayList<CardItem> = ArrayList()
        val index = 0
        list.add(CardItem(index + 0, "하체"))
        list.add(CardItem(index + 1, "하체"))
        list.add(CardItem(index + 2, "하체"))
        list.add(CardItem(index + 3, "하체"))
        list.add(CardItem(index + 4, "하체"))
        list.add(CardItem(index + 5, "하체"))
        list.add(CardItem(index + 6, "하체"))
        return list
    }

    override fun initWeekly(): ArrayList<CardItem> {
        var list: ArrayList<CardItem> = ArrayList()
        val index = 0
        list.add(CardItem(index, "등록된 운동이 없습니다."))
        list.add(CardItem(index + 1, "등록된 운동이 없습니다."))
        list.add(CardItem(index + 2, "등록된 운동이 없습니다."))
        list.add(CardItem(index + 3, "등록된 운동이 없습니다."))
        list.add(CardItem(index + 4, "등록된 운동이 없습니다."))
        list.add(CardItem(index + 5, "등록된 운동이 없습니다."))
        list.add(CardItem(index + 6, "등록된 운동이 없습니다."))
        return list
    }

    private fun getTag(viewId: Int): String =
        when (viewId) {
            R.id.view_monday -> "월요일"
            R.id.view_tuesday -> "화요일"
            R.id.view_wednesday -> "수요일"
            R.id.view_thursday -> "목요일"
            R.id.view_friday -> "금요일"
            R.id.view_saturday -> "토요일"
            R.id.view_sunday -> "일요일"
            else -> ""
        }

}