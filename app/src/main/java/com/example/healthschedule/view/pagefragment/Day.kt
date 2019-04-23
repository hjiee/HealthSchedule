package com.example.healthschedule.view.pagefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthschedule.R
import com.example.healthschedule.utils.DateUtils
import kotlinx.android.synthetic.main.day.*

class Day : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.day, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_day.text = "day : ${DateUtils.getDay()}"
        tv_week.text = "week : ${DateUtils.getWeek()} 주차"
    }
}
