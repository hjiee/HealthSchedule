package com.example.healthschedule.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.healthschedule.R
import com.example.healthschedule.adapter.PageAdatper
import com.example.healthschedule.utils.DateUtils
import com.example.healthschedule.adapter.PageTransformer
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val adapter by lazy { PageAdatper(supportFragmentManager)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val date = DateUtils.instance.get(Calendar.DAY_OF_WEEK) // 오늘 요일값

        viewpager.adapter = MainActivity@adapter
        viewpager.setPageTransformer(true, PageTransformer())
        val dpValue = 16
        val d= resources.displayMetrics.density
        val margin : Int = (dpValue*d).toInt()
        viewpager.setPadding(margin,0,margin,0)
        viewpager.setPageMargin(margin/2)
        viewpager.setCurrentItem(date-1)

    }

}
