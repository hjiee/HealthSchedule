package com.example.healthschedule

import org.junit.Test

import org.junit.Assert.*
import java.lang.Exception
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        try {
            val calendar = Calendar.getInstance()
            calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE))
            println(calendar.time)
            println("YEAR : "+calendar.get(Calendar.YEAR))
            println("MONTH : "+(calendar.get(Calendar.MONTH)+1))
            println("DATE : "+calendar.get(Calendar.DATE))

            calendar.add(Calendar.DATE,-1)
            println(calendar.time)


            println("YEAR : "+calendar.get(Calendar.YEAR))
            println("MONTH : "+(calendar.get(Calendar.MONTH)+1))
            println("DATE : "+calendar.get(Calendar.DATE))

            println("-----------------------------------")

            calendar.set(2019,Calendar.JANUARY,1)
            println(calendar.time)

            println("YEAR : "+calendar.get(Calendar.YEAR))
            println("MONTH : "+(calendar.get(Calendar.MONTH)+1))
            println("DATE : "+calendar.get(Calendar.DATE))

            calendar.add(Calendar.DATE,-1)
            println(calendar.time)

            println("YEAR : "+calendar.get(Calendar.YEAR))
            println("MONTH : "+(calendar.get(Calendar.MONTH)+1))
            println("DATE : "+calendar.get(Calendar.DATE))
        }
        catch (e : Exception) {

        }
    }
}
