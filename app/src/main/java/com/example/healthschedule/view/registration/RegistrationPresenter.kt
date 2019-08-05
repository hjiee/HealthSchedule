package com.example.healthschedule.view.registration

import android.content.DialogInterface
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import com.example.healthschedule.R
import com.example.healthschedule.adapter.workout.WorkoutAdapterContract
import com.example.healthschedule.data.CardItem
import com.example.healthschedule.data.source.WeeklyWorkoutRepository
import com.example.healthschedule.data.source.WeeklyWorkoutSource
import com.example.healthschedule.utils.ToastUtils.showToast
import com.example.healthschedule.view.registration.dialog.BringWorkoutDialogFragment
import com.example.healthschedule.view.registration.dialog.RegistrationDialogFragment
import com.example.healthschedule.view.registration.dto.ResultWorkoutDto
import kotlinx.android.synthetic.main.custom_view_registartion_item.view.*

class RegistrationPresenter : RegistrationContract.Presenter {

    lateinit var regView: RegistrationContract.View
    lateinit var workoutAdapterView: WorkoutAdapterContract.AdaterView
    lateinit var workoutAdapterModel: WorkoutAdapterContract.AdapterModel
    override lateinit var weeklyWorkoutData: WeeklyWorkoutRepository
    var resultWorkoutList = MutableList<ResultWorkoutDto?>(7) { null }

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
                            // 선택된 운동 목록을 표사한다.

                            resultWorkoutList[resultWorkoutDto.position] = resultWorkoutDto

                            view.tv_workout_hint.visibility = View.GONE
                            var stringBufferTitle = StringBuffer()
                            var stringBufferName = StringBuffer()
                            for (str in resultWorkoutDto.items) {
                                stringBufferTitle.append("${str?.title}\n")
                                stringBufferName.append("${str?.name}\n")
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


//    override fun addWeekly(): ArrayList<CardItem> {
//        var list: ArrayList<CardItem> = ArrayList()
//        val index = 0
//        list.add(CardItem(index + 0, "하체"))
//        list.add(CardItem(index + 1, "하체"))
//        list.add(CardItem(index + 2, "하체"))
//        list.add(CardItem(index + 3, "하체"))
//        list.add(CardItem(index + 4, "하체"))
//        list.add(CardItem(index + 5, "하체"))
//        list.add(CardItem(index + 6, "하체"))
//        return list
//    }
//
//    override fun initWeekly(): ArrayList<CardItem> {
//        var list: ArrayList<CardItem> = ArrayList()
//        val index = 0
//        list.add(CardItem(index, "등록된 운동이 없습니다."))
//        list.add(CardItem(index + 1, "등록된 운동이 없습니다."))
//        list.add(CardItem(index + 2, "등록된 운동이 없습니다."))
//        list.add(CardItem(index + 3, "등록된 운동이 없습니다."))
//        list.add(CardItem(index + 4, "등록된 운동이 없습니다."))
//        list.add(CardItem(index + 5, "등록된 운동이 없습니다."))
//        list.add(CardItem(index + 6, "등록된 운동이 없습니다."))
//        return list
//    }

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

    override fun showAlterDialog(builder: AlertDialog.Builder) {

        builder.run {
            setMessage("등록하시겠습니까?")
            setPositiveButton("Ok") { _, _ ->

                //TODO 파이어베이스 디비에 데이터를 저장하기.
                
                for (i in resultWorkoutList) {
                    Log.e("test", i?.day ?: "null")
                }
//                regView.showToast("")

                regView.returnToResult()

            }
            setNegativeButton("Cancel") { _, _ ->
                regView.showToast("취소")
            }

            builder.show()
        }
    }
}

