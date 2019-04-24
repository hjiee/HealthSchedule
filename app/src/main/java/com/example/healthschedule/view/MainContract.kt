package com.example.healthschedule.view

import com.example.healthschedule.adapter.PageAdapter
import com.example.healthschedule.adapter.PageContract

interface MainContract {

    interface Presenter {
        var view : MainContract.View

        var pageAdapterView : PageContract.View

        var pagerAdapterModel : PageContract.Model

        fun initView()


    }
    interface View {
        fun showToast(message : String)
    }
}