package com.example.healthschedule.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getDate(viewPagePosition: Int): String {
        // 오늘 날짜에서 스크롤링된 페이지의 날짜를 구한다.
        // + 현재페이보다 오른쪽으로 스크롤링됨
        // - 현재페이지보다 왼쪽으로 스크롤링됨
        return (viewPagePosition.minus(getTodayPosition())).let {
            when (it) {
                0 -> getToday()
                else -> {
                    if(getTodayPosition() == -1) getOtherDay(it-7)
                    else getOtherDay(it)
                }
            }
        }
    }

    fun getSimpleDateFormat(date: Date) = SimpleDateFormat("yyyy년 MM월 dd일 E요일").format(date).let { it }

    fun getTodayPosition() = (getCalendar().let { it.get(Calendar.DAY_OF_WEEK) - 2 }) // (일요일=-1 , 토요일=5)
//    fun getTodayPosition() = 5 // (일요일=-1 , 토요일=5)

    fun getToday() = getSimpleDateFormat(Date())

    fun getOtherDay(amount: Int) = getCalendar().let {
        it.add(Calendar.DAY_OF_MONTH, amount)
        getSimpleDateFormat(Date(it.timeInMillis))
    }

    fun getMonth() = getCalendar().let { it.get(Calendar.MONTH)+1 }.toString()
    fun getWeek() = getCalendar().let { it.get(Calendar.WEEK_OF_MONTH) }.toString()
    fun getCalendar() = Calendar.getInstance(TimeZone.getDefault())

}