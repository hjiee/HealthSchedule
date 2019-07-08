package com.example.healthschedule.adapter.page

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.healthschedule.data.CardItem
import com.example.healthschedule.view.main.DayViewPageFragment
import java.util.ArrayList

class PageAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm),
    PageAdapterContract.Model,
    PageAdapterContract.View {
    private val pageCount = 7
    private val mData: MutableList<CardItem>

    init {
        mData = ArrayList()
    }

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
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

