package com.example.healthschedule.view.main

import android.content.Context
import android.view.View
import com.example.healthschedule.adapter.PageAdapterContract

interface MainContract {

    interface Presenter {
        var view : View

        var pageAdapterAdapterView : PageAdapterContract.View

        var pagerAdapterModel : PageAdapterContract.Model

        fun anim(view : android.view.View)

        fun redirection(view : android.view.View, context : Context)


        fun initView()


    }
    interface View {
        fun showToast(message : String)

        fun showToggle(isOpen : Boolean)
    }
}