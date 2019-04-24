package com.example.healthschedule.view

import com.example.healthschedule.adapter.PageContract

class MainPresenter : MainContract.Presenter {
    override lateinit var view: MainContract.View
    override lateinit var pagerAdapterModel: PageContract.Model
    override lateinit var pageAdapterView: PageContract.View

    override fun initView() {

    }

}