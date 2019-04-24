package com.example.healthschedule.adapter

import com.example.healthschedule.utils.CardItem

interface PageContract {

    interface Model {
        fun addItem(item : CardItem)
        fun deleteItem(position : Int)
    }
    interface View {


    }
}