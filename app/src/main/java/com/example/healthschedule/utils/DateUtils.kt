package com.example.healthschedule.utils
import java.util.*

class DateUtils {
    companion object {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        val week = calendar.get(Calendar.DAY_OF_MONTH) - 1 // 한주의 요일을 구한다.(일 = 0, 토 = 6)
        val weekly = calendar.get(Calendar.WEEK_OF_MONTH) // 현재 주차를 구한다.
        val month = calendar.get(Calendar.MONTH) + 1 // 월을 구한다. (1월 = 1)


        // 날짜를 반환한다.
        fun getDate(todayPosition : Int , currentPosition: Int): String {
            val date = Calendar.getInstance(TimeZone.getDefault())

            // 오늘 날짜에서 스크롤릴된 페이지의 날짜를 구한다.
            // + 현재페이보다 오른쪽으로 스크롤링됨
            // - 현재페이지보다 왼쪽으로 스크롤링됨
            val position = currentPosition - todayPosition

            date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DATE))
            date.add(date.get(Calendar.DATE),position)
            return "${date.get(Calendar.YEAR)}년 ${date.get(Calendar.MONTH) + 1}월 ${date.get(Calendar.DATE)}일 ${getWeek(currentPosition)}"
        }


        // 요일을 반환한다.
        fun getWeek(viewpagePosition: Int?): String {
            return when (viewpagePosition) {
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
    }
}


