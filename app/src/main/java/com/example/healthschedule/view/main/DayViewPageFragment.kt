package com.example.healthschedule.view.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthschedule.R
import com.example.healthschedule.utils.DateUtils
import kotlinx.android.synthetic.main.day.*

class DayViewPageFragment : Fragment() {

    var cardView : CardView? = null
        private set

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.day,container,false)
//        cardView = view.findViewById(R.id.cardview) as CardView
//        cardView!!.maxCardElevation = cardView!!.cardElevation * 8
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = arguments!!.getInt(EXTRA_POSITION)
        tv_day.text = "${DateUtils.getDay(position)}"
        tv_week.text = "${DateUtils.month}월 ${DateUtils.getWeek()} 주차"

    }

    companion object {
        private const val EXTRA_POSITION = "EXTRA_POSITION"
        fun newInstance(postion : Int) = DayViewPageFragment().apply {
            arguments = Bundle().apply {
                putInt(EXTRA_POSITION,postion)
            }
        }
    }
}
