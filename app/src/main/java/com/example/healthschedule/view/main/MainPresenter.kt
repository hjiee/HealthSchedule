package com.example.healthschedule.view.main


import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import com.example.healthschedule.adapter.PageAdapterContract
import com.example.healthschedule.view.calendar.CalendarActivity

class MainPresenter : MainContract.Presenter {
    var isFabOpen: Boolean = false
    override lateinit var view: MainContract.View
    override lateinit var pagerAdapterModel: PageAdapterContract.Model
    override lateinit var pageAdapterAdapterView: PageAdapterContract.View

    override fun initView() {

    }

    override fun anim(view: View) = when (isFabOpen) {
        true -> showAnim()
        false -> hideAnim()
    }

    override fun redirection(view: View, context: Context) {

    }

    private fun showAnim() {
        view.showToggle(true)
        isFabOpen = false
    }

    private fun hideAnim() {
        view.showToggle(false)
        isFabOpen = true
    }
}