package com.example.healthschedule.view.pagefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthschedule.R
import com.example.healthschedule.utils.DateUtil
import kotlinx.android.synthetic.main.day.*

class DayFragment : Fragment() {

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
        tv_day.text = "${DateUtil.getDay(position)}"
        tv_week.text = "${DateUtil.month}월 ${DateUtil.getWeek()} 주차"

    }

    companion object {
        private const val EXTRA_POSITION = "EXTRA_POSITION"
        fun newInstance(postion : Int) = DayFragment().apply {
            arguments = Bundle().apply {
                putInt(EXTRA_POSITION,postion)
            }
        }
    }
}
