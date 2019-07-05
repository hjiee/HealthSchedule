package com.example.healthschedule.utils

import java.util.*

class DateUtils {
    companion object {
        val instance = Calendar.getInstance()
        val week = instance.get(Calendar.DAY_OF_MONTH) - 2 // 한주의 요일을 구한다.(일 = -1, 토 = 5)
        val weekly = instance.get(Calendar.WEEK_OF_MONTH)

        val year = instance.get(Calendar.YEAR)
        val month = instance.get(Calendar.MONTH) + 1 // 월을 구한다. (1월 = 1)
        val day = instance.get(Calendar.DAY_OF_MONTH) // 현재 날짜(일)


        // 날짜를 반환한다.
        fun getDate(todayPosition : Int , currentPosition: Int): String =
            "${instance.get(Calendar.YEAR)}년 ${instance.get(Calendar.MONTH) + 1}월 ${getDay(todayPosition,currentPosition)}일 ${getWeek(currentPosition)}"



        // 요일을 반환한다.
        fun getWeek(position: Int?): String {
            return when (position) {
                0 -> "월요일"
                1 -> "화요일"
                2 -> "수요일"
                3 -> "목요일"
                4 -> "금요일"
                5 -> "토요일"
                6 -> "일요일"
                else -> ""
            }
        }

        fun getDay(todayPsition: Int, currentPosition : Int) : String {
            // 오늘 날짜에서 스크롤릴된 페이지의 날짜를 구한다.
            // + 현재페이보다 오른쪽으로 스크롤링됨
            // - 현재페이지보다 왼쪽으로 스크롤릴됨
            val position = currentPosition - todayPsition
            return when {
                position == 0 -> "${instance.get(Calendar.DAY_OF_MONTH)-1}"
                position > 0 -> "${instance.get(Calendar.DAY_OF_MONTH)+position-1}"
                position < 0 -> "${instance.get(Calendar.DAY_OF_MONTH)+position-1}"
                else -> ""
            }
        }

        // 해당 월에 주차를 반환한다.
        fun getWeek(): String {
            return weekly.toString()
        }
    }
}


