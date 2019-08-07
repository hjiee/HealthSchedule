package com.example.healthschedule.view.registration

import android.annotation.SuppressLint
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.example.healthschedule.R
import com.example.healthschedule.adapter.workout.WorkoutAdapterContract
import com.example.healthschedule.data.WeeklyWorkoutRepository
import com.example.healthschedule.data.dto.CardItem
import com.example.healthschedule.data.source.WeeklyWorkoutSource
import com.example.healthschedule.utils.DateUtils.getDate
import com.example.healthschedule.view.registration.dialog.BringWorkoutDialogFragment
import com.example.healthschedule.view.registration.dialog.RegistrationDialogFragment
import com.example.healthschedule.view.registration.dto.ResultWorkoutDto
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Path
import kotlinx.android.synthetic.main.custom_view_registartion_item.view.*

class RegistrationPresenter : RegistrationContract.Presenter {

    lateinit var regView: RegistrationContract.View
    lateinit var workoutAdapterView: WorkoutAdapterContract.AdaterView
    lateinit var workoutAdapterModel: WorkoutAdapterContract.AdapterModel
    override lateinit var weeklyWorkoutData: WeeklyWorkoutRepository
    lateinit var resultWorkoutList : MutableList<ResultWorkoutDto?>

    override fun registrationWorkout(workoutName: ArrayList<CardItem>) {
        weeklyWorkoutData.addWorkout(workoutName, object : WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                workoutAdapterModel.registrationWorkout(list)
                workoutAdapterView.notifyAdapter()
            }
        })
    }

    fun initList() : MutableList<ResultWorkoutDto?>{
        resultWorkoutList = MutableList<ResultWorkoutDto?>(0) { null }
        for(i in 0..6)
            resultWorkoutList.add(ResultWorkoutDto(getDate(i),0,null))
        return resultWorkoutList
    }

    override fun dialog(supportFragmentManager: FragmentManager, view: View) {
        when (view.id) {

            R.id.btn_bring -> BringWorkoutDialogFragment.newInstance().show(supportFragmentManager, "")
            else -> {
                RegistrationDialogFragment.newInstance().run {
                    show(supportFragmentManager, getTag(view.id))
                    // 요일별 추가된 아이템 콜백
                    setDailyWorkoutCallback(object : RegistrationDialogFragment.DailyWorkoutCallback {
                        override fun result(resultWorkoutDto: ResultWorkoutDto) {
                            // 추가된 운동 목록을 표시한다.
                            resultWorkoutList[resultWorkoutDto.position] = resultWorkoutDto

                            view.tv_workout_hint.visibility = View.GONE
                            var stringBufferTitle = StringBuffer()
                            var stringBufferName = StringBuffer()
                            for (str in resultWorkoutDto.workouts!!) {
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

    @SuppressLint("RestrictedApi")
    override fun showAlterDialog(builder: AlertDialog.Builder) {
        val database = FirebaseDatabase.getInstance().reference

//        for(data in resultWorkoutList) {
//            database.child("hyojin").child(data?.date.toString()).setValue(resultWorkoutList)
//        }
        for(i in 0..resultWorkoutList.size-1) {
            val date = database.child("hyojin").child(resultWorkoutList[i]?.date.toString())
//            if(date.key.equals(null)) {
                val map = HashMap<String,Any?>()
                map[date.path.toString()] = (resultWorkoutList[i]?.workouts)
                database.updateChildren(map)
//            }
//            else {
//                date
//                    .setValue(resultWorkoutList[i]?.workouts)
//                    .addOnSuccessListener { }
//                    .addOnFailureListener { }
//            }
        }
//        builder.run {
//            setMessage("등록하시겠습니까?")
//            setPositiveButton("Ok") { _, _ ->
//
//                //TODO 파이어베이스 디비에 데이터를 저장하기.
//
//
//                for (i in resultWorkoutList) {
//                    Log.e("test", i?.day ?: "null")
//                }
////                regView.showToast("")
//
//                regView.returnToResult()
//
//            }
//            setNegativeButton("Cancel") { _, _ ->
//                regView.showToast("취소")
//            }
//
//            builder.show()
//        }
    }
}

