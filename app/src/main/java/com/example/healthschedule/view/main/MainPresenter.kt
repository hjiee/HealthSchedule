package com.example.healthschedule.view.main


import android.content.Context
import android.view.View
import com.example.healthschedule.adapter.page.PageAdapterContract
import com.example.healthschedule.adapter.workout.WorkoutAdapterContract
import com.example.healthschedule.data.CardItem
import com.example.healthschedule.data.source.WeeklyWorkoutRepository
import com.example.healthschedule.data.source.WeeklyWorkoutSource
import com.example.healthschedule.utils.DateUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainPresenter : MainContract.Presenter {
    var isFabOpen: Boolean = false
    override lateinit var view: MainContract.View
    override lateinit var pageAdapterAdapterView: PageAdapterContract.View
    override lateinit var pagerAdapterModel: PageAdapterContract.Model
    override lateinit var workoutAdapterView: WorkoutAdapterContract.AdaterView
    override lateinit var workoutAdapterModel: WorkoutAdapterContract.AdapterModel
    override lateinit var weeklyWorkoutData: WeeklyWorkoutRepository


    override fun anim(view: View) = when (isFabOpen) {
        true -> showAnim()
        false -> hideAnim()
    }


    override fun getViewPagerMargin(context: Context): Int = context.resources.displayMetrics.density.let { density -> (16 * density).toInt() }

    override fun loadItems(postion : Int, isClear : Boolean) {
        weeklyWorkoutData.getWorkout(postion,object : WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                if(isClear)
                    workoutAdapterModel.clearItem()
                workoutAdapterModel.registrationWorkout(list)
                workoutAdapterView.notifyAdapter()
            }
        })
    }

    override fun getItem(postion: Int) = workoutAdapterModel.getItem(postion)

    private fun showAnim() {
        view.showToggle(true)
        isFabOpen = false
    }

    private fun hideAnim() {
        view.showToggle(false)
        isFabOpen = true
    }


    override fun registrationWorkout(workoutName : ArrayList<CardItem>) {
        weeklyWorkoutData.addWorkout(workoutName,object : WeeklyWorkoutSource.LoadCallback {
            override fun onLoad(list: ArrayList<CardItem>) {
                workoutAdapterModel.registrationWorkout(list)
                workoutAdapterView?.notifyAdapter()
            }
        })
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