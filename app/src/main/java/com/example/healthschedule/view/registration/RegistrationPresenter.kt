package com.example.healthschedule.view.registration

import android.content.Context
import androidx.appcompat.app.AlertDialog
import android.text.format.DateUtils
import android.view.LayoutInflater
import com.example.healthschedule.R
import com.example.healthschedule.adapter.workout.WorkoutAdapterContract
import com.example.healthschedule.data.CardItem
import com.example.healthschedule.data.source.WeeklyWorkoutRepository
import com.example.healthschedule.data.source.WeeklyWorkoutSource
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.custom_view_weekly_workout_item.view.*

class RegistrationPresenter : RegistrationContract.Presenter {

    lateinit var view : RegistrationContract.View
    lateinit var workoutAdapterView : WorkoutAdapterContract.AdaterView
    lateinit var workoutAdapterModel : WorkoutAdapterContract.AdapterModel
    override lateinit var weeklyWorkoutData: WeeklyWorkoutRepository

    override fun registrationWorkout(workoutName : ArrayList<CardItem>) {
        weeklyWorkoutData.addWorkout(workoutName,object : WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                workoutAdapterModel.registrationWorkout(list)
                workoutAdapterView?.notifyAdapter()
            }
        })
    }

    override fun dialog(context: Context,viewId : Int) {
        val builder = AlertDialog.Builder(context)
        val dialogView = LayoutInflater.from(context).inflate(R.layout.registration_dialog,null)
        var isSaved : Boolean = false
        builder.setView(dialogView)
            .setPositiveButton("저장") {
                    _, _ ->
                isSaved = true
                view.setDailyWorkout(viewId,isSaved.toString())
            }
            .setNegativeButton("취소") {
                    _, _ ->
                isSaved = false
                view.setDailyWorkout(viewId,isSaved.toString())
            }
            .show()
    }

    override fun addWeekly() : ArrayList<CardItem>{
        var list : ArrayList<CardItem> = ArrayList()
        val index = 0
        list.add(CardItem(index+0,"하체"))
        list.add(CardItem(index+1,"하체"))
        list.add(CardItem(index+2,"하체"))
        list.add(CardItem(index+3,"하체"))
        list.add(CardItem(index+4,"하체"))
        list.add(CardItem(index+5,"하체"))
        list.add(CardItem(index+6,"하체"))
        return list
    }

    override fun initWeekly() : ArrayList<CardItem> {
        var list : ArrayList<CardItem> = ArrayList()
        val index = 0
        list.add(CardItem(index,"등록된 운동이 없습니다."))
        list.add(CardItem(index+1,"등록된 운동이 없습니다."))
        list.add(CardItem(index+2,"등록된 운동이 없습니다."))
        list.add(CardItem(index+3,"등록된 운동이 없습니다."))
        list.add(CardItem(index+4,"등록된 운동이 없습니다."))
        list.add(CardItem(index+5,"등록된 운동이 없습니다."))
        list.add(CardItem(index+6,"등록된 운동이 없습니다."))
        return list
    }
}