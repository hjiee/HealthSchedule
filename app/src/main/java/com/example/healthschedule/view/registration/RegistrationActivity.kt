package com.example.healthschedule.view.registration

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.healthschedule.R
import com.example.healthschedule.adapter.workout.WorkoutAdapter
import com.example.healthschedule.base.BaseActivity
import com.example.healthschedule.data.source.WeeklyWorkoutRepository
import com.example.healthschedule.utils.CommonUtils.Companion.REQUEST_CODE_REGISTRATION
import com.example.healthschedule.utils.ToastUtils.showToast
import com.example.healthschedule.view.main.MainActivity
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.custom_view_registartion_item.view.*

class RegistrationActivity : BaseActivity(), RegistrationContract.View {
    lateinit var presenter: RegistrationPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val wokroutAdapter = WorkoutAdapter()

        presenter = RegistrationPresenter().apply {
            regView = this@RegistrationActivity
            workoutAdapterView = wokroutAdapter
            workoutAdapterModel = wokroutAdapter
            weeklyWorkoutData = WeeklyWorkoutRepository
        }

//        presenter.registrationWorkout(presenter.initWeekly())


        fab_check.setOnClickListener {
            presenter.showAlterDialog(AlertDialog.Builder(this))
//            super.showAlterDialog()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
        finish()

    }

    override fun setDailyWorkout(viewId: Int, result: String) {
        when (viewId) {
            R.id.view_monday -> view_monday.tv_workout_title.text = result
            R.id.view_tuesday -> view_tuesday.tv_workout_title.text = result
            R.id.view_wednesday -> view_wednesday.tv_workout_title.text = result
            R.id.view_thursday -> view_thursday.tv_workout_title.text = result
            R.id.view_friday -> view_friday.tv_workout_title.text = result
            R.id.view_saturday -> view_saturday.tv_workout_title.text = result
            R.id.view_sunday -> view_sunday.tv_workout_title.text = result
            else -> showToast(resources.getString(R.string.NotFound))
        }
    }

    override fun returnToResult() {
        setResult(REQUEST_CODE_REGISTRATION)
        finish()
    }

    fun onClick(view: View) {
        presenter.dialog(supportFragmentManager, view)
    }
}