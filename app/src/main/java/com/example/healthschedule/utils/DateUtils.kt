package com.example.healthschedule.utils

import java.util.*

class DateUtils {
    companion object {
        val instance = Calendar.getInstance()
        val date = instance.get(Calendar.DAY_OF_WEEK) // 한주의 요일을 구한다.(일 = 1)

        // 요일을 반환한다.
        fun getDay() : String {
            return when (date) {
                1 -> "일요일"
                2 -> "월요일"
                3 -> "화요일"
                4 -> "수요일"
                5 -> "목요일"
                6 -> "금요일"
                7 -> "토요일"
                else -> ""
            }
        }
        // 해당 월에 주차를 반환한다.
        fun getWeek() : String {
            val week = instance.get(Calendar.WEEK_OF_MONTH)
            return week.toString()
        }
    }
}