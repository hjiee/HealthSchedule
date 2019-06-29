package com.example.healthschedule.utils

import java.util.*

class DateUtils {
    companion object {
        val instance = Calendar.getInstance()
        val day = instance.get(Calendar.DAY_OF_WEEK)-1 // 한주의 요일을 구한다.(일 = 0)
        val week = instance.get(Calendar.WEEK_OF_MONTH)
        val month = instance.get(Calendar.MONTH)+1 // 월을 구한다.

        // 요일을 반환한다.
        fun getDay(position : Int?) : String {
            val revise = 0
            return when (position) {
                revise+0 -> "일요일"
                revise+1 -> "월요일"
                revise+2 -> "화요일"
                revise+3 -> "수요일"
                revise+4 -> "목요일"
                revise+5 -> "금요일"
                revise+6 -> "토요일"
                else -> ""
            }
        }

        // 해당 월에 주차를 반환한다.
        fun getWeek() : String {
            return week.toString()
        }
    }


}