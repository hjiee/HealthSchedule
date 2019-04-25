package com.example.healthschedule.view.calendar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.healthschedule.R
import com.example.healthschedule.utils.ToastUtils.Companion.showMessage

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        showMessage("운동달력")
    }
}