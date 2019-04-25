package com.example.healthschedule.view.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View

class test : AppCompatActivity() {

    private val viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewPager!!.setOnClickListener(View.OnClickListener { })
    }
}
