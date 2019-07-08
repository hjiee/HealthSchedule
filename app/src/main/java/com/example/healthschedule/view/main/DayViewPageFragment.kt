package com.example.healthschedule.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthschedule.R
import com.example.healthschedule.base.BaseFragment
import com.example.healthschedule.utils.DateUtils.getDate
import com.example.healthschedule.utils.DateUtils.getMonth
import com.example.healthschedule.utils.DateUtils.getWeek
import kotlinx.android.synthetic.main.fragment_weekly_workout.*

class DayViewPageFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weekly_workout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getInt(EXTRA_POSITION)?.let {
            tv_week.text = getDate(it)
            tv_weekly.text = "${getMonth()}월 ${getWeek()}주차"

        }

    }

    companion object {
        private const val EXTRA_POSITION = "EXTRA_POSITION"
        fun newInstance(postion: Int) = DayViewPageFragment().apply {
            arguments = Bundle().apply {
                putInt(EXTRA_POSITION, postion)
            }
        }
    }
}
