package com.example.healthschedule.adapter.page

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.healthschedule.data.CardItem
import com.example.healthschedule.view.main.DayViewPageFragment
import java.util.ArrayList

class PageAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm),
    PageAdapterContract.Model,
    PageAdapterContract.View {
    private val pageCount = 7
    private val mData: MutableList<CardItem>

    init {
        mData = ArrayList()
    }

    override fun getItem(position: Int): Fragment {
        return DayViewPageFragment.newInstance(position)
    }

    override fun getCount() = pageCount

    override fun addItem(item: CardItem) {
        mData.add(item)
    }

    override fun deleteItem(position: Int) {
        mData.removeAt(position)
    }
}

