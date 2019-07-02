package com.example.healthschedule.view.main


import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.healthschedule.R
import com.example.healthschedule.adapter.page.PageAdapterContract
import com.example.healthschedule.adapter.workout.WorkoutAdapterContract
import com.example.healthschedule.data.CardItem
import com.example.healthschedule.data.source.WeeklyWorkoutRepository
import com.example.healthschedule.data.source.WeeklyWorkoutSource

class MainPresenter : MainContract.Presenter {
    var isButtonExtended: Boolean = false
    override lateinit var mainView: MainContract.View
    override lateinit var pageAdapterAdapterView: PageAdapterContract.View
    override lateinit var pagerAdapterModel: PageAdapterContract.Model
    override lateinit var workoutAdapterView: WorkoutAdapterContract.AdaterView
    override lateinit var workoutAdapterModel: WorkoutAdapterContract.AdapterModel
    override lateinit var weeklyWorkoutData: WeeklyWorkoutRepository


    override fun anim() {
        val fabOpen: Animation = AnimationUtils.loadAnimation(mainView as Context, R.anim.fab_open)
        val fabClose: Animation = AnimationUtils.loadAnimation(mainView as Context, R.anim.fab_close)
        val fabRotateOpen: Animation = AnimationUtils.loadAnimation(mainView as Context, R.anim.fab_rotate_open)
        val fabRotateClose: Animation = AnimationUtils.loadAnimation(mainView as Context, R.anim.fab_rotate_close)

        when (isButtonExtended) {
            true -> {
                mainView.hideSubFab(fabClose, fabRotateClose)
                mainView.isExpanded(false)
            }
            false -> {
                mainView.showSubFab(fabOpen, fabRotateOpen)
                mainView.isExpanded(true)
            }
        }
    }


    override fun getViewPagerMargin(context: Context): Int =
        context.resources.displayMetrics.density.let { density -> (16 * density).toInt() }

    override fun loadItems(postion: Int, isClear: Boolean) {
        weeklyWorkoutData.getWorkout(postion, object : WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                if (isClear)
                    workoutAdapterModel.clearItem()
                workoutAdapterModel.registrationWorkout(list)
                workoutAdapterView.notifyAdapter()
            }
        })
    }

    override fun getItem(postion: Int) = workoutAdapterModel.getItem(postion)


    override fun registrationWorkout(workoutName: ArrayList<CardItem>) {
        weeklyWorkoutData.addWorkout(workoutName, object : WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                workoutAdapterModel.registrationWorkout(list)
                workoutAdapterView?.notifyAdapter()
            }
        })
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
}