package com.example.healthschedule.adapter.page

import com.example.healthschedule.data.CardItem

interface PageAdapterContract {

    interface Model {
        fun addItem(item : CardItem)
        fun deleteItem(position : Int)
    }
    interface View {


    }
}