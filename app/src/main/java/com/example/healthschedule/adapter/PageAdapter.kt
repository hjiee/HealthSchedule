package com.example.healthschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.CardView
import com.example.healthschedule.utils.CardItem
import com.example.healthschedule.view.pagefragment.*
import java.util.ArrayList

class PageAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm), PageContract.Model,PageContract.View {
    private val pageCount = 7
    private val mData: MutableList<CardItem>

    init {
        mData = ArrayList()
    }

    override fun getItem(position: Int): Fragment {
        return DayFragment.newInstance(position)
    }

    override fun getCount() = pageCount

    override fun addItem(item: CardItem) {
        mData.add(item)
    }

    override fun deleteItem(position: Int) {
        mData.removeAt(position)
    }
}

