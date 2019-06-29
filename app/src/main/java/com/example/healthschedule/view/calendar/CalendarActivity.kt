package com.example.healthschedule.view.calendar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.healthschedule.R
import com.example.healthschedule.base.BaseActivity
import com.example.healthschedule.utils.ToastUtils.showToast
import kotlinx.android.synthetic.main.activity_main.*


class CalendarActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        showToast("운동달력")

    }
}