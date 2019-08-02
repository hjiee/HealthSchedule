package com.example.healthschedule.utils

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.healthschedule.R
import kotlinx.android.synthetic.main.custom_view_registartion_item.view.*


class CustomView(context: Context, attrs: AttributeSet) : LinearLayout(context,attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_view_registartion_item,this,true)
        val attributes = context.obtainStyledAttributes(attrs,R.styleable.CustomView)
        iv_day.setImageDrawable(attributes.getDrawable(R.styleable.CustomView_image))
        tv_workout_title.text = attributes.getString(R.styleable.CustomView_text)
        attributes.recycle()

    }
}

