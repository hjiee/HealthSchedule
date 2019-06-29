package com.example.healthschedule.view.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.healthschedule.R
import com.example.healthschedule.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_weekly_workout.*

class DayViewPageFragment : Fragment() {

    var cardView: CardView? = null
        private set



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         val staticPosition = arguments?.getInt(EXTRA_POSITION)
        Log.e("LifeTest","onCreate : $staticPosition")
    }

    override fun onStart() {
        super.onStart()
         val staticPosition = arguments?.getInt(EXTRA_POSITION)
        Log.e("LifeTest","onStart : $staticPosition")
    }

    override fun onResume() {
        super.onResume()
         val staticPosition = arguments?.getInt(EXTRA_POSITION)
        Log.e("LifeTest","onResume : $staticPosition")
        Log.e("LifeTest","------------------------------------------------- : $staticPosition")
    }

    override fun onDestroy() {
        super.onDestroy()
         val staticPosition = arguments?.getInt(EXTRA_POSITION)
        Log.e("LifeTest","onStart : $staticPosition")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
         val staticPosition = arguments?.getInt(EXTRA_POSITION)
        Log.e("LifeTest","onAttach : $staticPosition : Context")
    }

    override fun onDetach() {
        super.onDetach()
         val staticPosition = arguments?.getInt(EXTRA_POSITION)
        Log.e("LifeTest","onDetach ★★★★★★★★★★★★★★★★★★★★★★★★★: $staticPosition")
    }

    override fun onDestroyView() {
        super.onDestroyView()
         val staticPosition = arguments?.getInt(EXTRA_POSITION)
        Log.e("LifeTest","onDestroyView : $staticPosition")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         val staticPosition = arguments?.getInt(EXTRA_POSITION)
        Log.e("LifeTest","onCreateVIew : $staticPosition")
        val view = inflater.inflate(R.layout.fragment_weekly_workout, container, false)
//        cardView = view.findViewById(R.id.cardview) as CardView
//        cardView!!.maxCardElevation = cardView!!.cardElevation * 8
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = arguments?.getInt(EXTRA_POSITION)
         val staticPosition = arguments?.getInt(EXTRA_POSITION)
        Log.e("LifeTest","onViewCreated : $staticPosition")
        tv_day.text = "${DateUtils.getDay(position)}"
        tv_week.text = "${DateUtils.month}월 ${DateUtils.getWeek()} 주차"

    }

    companion object {
        private const val EXTRA_POSITION = "EXTRA_POSITION"
        fun newInstance(postion: Int) = DayViewPageFragment().apply {
            Log.e("LifeTest","newinstance : $postion")
            arguments = Bundle().apply {
                putInt(EXTRA_POSITION, postion)
            }
        }
    }
}
