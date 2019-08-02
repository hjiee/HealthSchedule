package com.example.healthschedule.view.registration

import android.view.View
import androidx.fragment.app.FragmentManager
import com.example.healthschedule.R
import com.example.healthschedule.adapter.workout.WorkoutAdapterContract
import com.example.healthschedule.data.CardItem
import com.example.healthschedule.data.source.WeeklyWorkoutRepository
import com.example.healthschedule.data.source.WeeklyWorkoutSource
import com.example.healthschedule.view.registration.dto.ResultWorkoutDto
import kotlinx.android.synthetic.main.custom_view_registartion_item.view.*

class RegistrationPresenter : RegistrationContract.Presenter {

    lateinit var regView: RegistrationContract.View
    lateinit var workoutAdapterView: WorkoutAdapterContract.AdaterView
    lateinit var workoutAdapterModel: WorkoutAdapterContract.AdapterModel
    override lateinit var weeklyWorkoutData: WeeklyWorkoutRepository

    override fun registrationWorkout(workoutName: ArrayList<CardItem>) {
        weeklyWorkoutData.addWorkout(workoutName, object : WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                workoutAdapterModel.registrationWorkout(list)
                workoutAdapterView.notifyAdapter()
            }
        })
    }

    override fun dialog(supportFragmentManager: FragmentManager, view: View) {
        when (view.id) {
            R.id.btn_bring -> BringWorkoutDialogFragment.newInstance().show(supportFragmentManager, "")
            else -> {
                RegistrationDialogFragment.newInstance().run {
                    show(supportFragmentManager, getTag(view.id))
                    setDailyWorkoutCallback(object : RegistrationDialogFragment.DailyWorkoutCallback {
                        override fun result(resultWorkoutDto: ResultWorkoutDto) {
//                             var stringBuffer = StringBuffer("${resultWorkoutDto.day}\n")
                            // 선택된 운동 목록을 표사한다.
                            view.tv_workout_hint.visibility = View.GONE
                            var stringBufferTitle = StringBuffer()
                            var stringBufferName = StringBuffer()
                            for (str in resultWorkoutDto.items) {
                                stringBufferTitle.append("${str.title}\n")
                                stringBufferName.append("${str.name}\n")
                            }
                            view.tv_workout_title.text = stringBufferTitle
                            view.tv_workout_name.text = stringBufferName

                            view.cardview.run {
                                setCardBackgroundColor(resources.getColor(R.color.colorSelected, null))
                            }

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