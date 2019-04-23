package com.example.healthschedule.view.pagefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthschedule.R
import kotlinx.android.synthetic.main.day.*

/**
 * 금요일
 */
class Fri : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.day, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tv_day.text = "금요일"
        tv_week.text = "week $tv_day"
    }

}