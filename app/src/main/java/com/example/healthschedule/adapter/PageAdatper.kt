package com.example.healthschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.healthschedule.utils.DateUtils
import com.example.healthschedule.view.pagefragment.*

class PageAdatper(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    private val pageCount = 7
    override fun getItem(position: Int): Fragment? {



//        return when (position) {
//            0 -> {
//                Mon()
//            }
//            1 -> {
//                Tue()
//            }
//            2 -> {
//                Wed()
//            }
//            3 -> {
//                Thu()
//            }
//            4 -> {
//                Fri()
//
//            }
//            5 -> {
//                Sat()
//            }
//            6 -> {
//                Sun()
//            }
//
//            else -> null
//        }
        when (position) {

        }
        DateUtils.date

        return Day()
    }

    override fun getCount() = pageCount
}