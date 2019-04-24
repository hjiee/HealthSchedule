package com.example.healthschedule.view

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.healthschedule.R
import com.example.healthschedule.adapter.PageAdapter
import com.example.healthschedule.utils.DateUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    //    private val presenter : MainPresenter by lazy { MainPresenter() }
    private lateinit var presenter: MainPresenter
    private lateinit var pageAdapter: PageAdapter
    private var isFabOpen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        pageAdapter = PageAdapter(supportFragmentManager)


        presenter = MainPresenter().apply {
            view = this@MainActivity
            pageAdapterView = pageAdapter
            pagerAdapterModel = pageAdapter
        }

        val dpValue = 16
        val d = resources.displayMetrics.density
        val margin: Int = (dpValue * d).toInt()



        viewpager.adapter = pageAdapter
//        viewpager.setPageTransformer(true, PageTransformer())
        viewpager.offscreenPageLimit = 7
        viewpager.setPadding(margin * 5, margin, margin * 5, margin)
        viewpager.pageMargin = (margin / 2)
        viewpager.currentItem = (DateUtil.day) // 현재 요일


        val fabOpen = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open)
        val fabClose = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close)
        fab.setOnClickListener {
            anim(fabClose, fabOpen)
            presenter.view.showToast("Test")
        }
        fab1.setOnClickListener {
            anim(fabClose, fabOpen)
            presenter.view.showToast("Fab1")
        }
        fab2.setOnClickListener {
            anim(fabClose, fabOpen)
            presenter.view.showToast("Fab2")
        }


    }

    override fun showToast(message: String) {
        Toast.makeText(this, "Message : $message", Toast.LENGTH_SHORT).show()
    }

    fun anim(close: Animation, open: Animation) {
        if (isFabOpen) {
            fab1.startAnimation(close)
            fab2.startAnimation(close)
            fab1.isClickable = false
            fab2.isClickable = false
            fab1.visibility = View.VISIBLE
            fab2.visibility = View.VISIBLE
            isFabOpen = false
        } else {
            fab1.startAnimation(open)
            fab2.startAnimation(open)
            fab1.isClickable = true
            fab2.isClickable = true
            fab1.visibility = View.INVISIBLE
            fab2.visibility = View.INVISIBLE
            isFabOpen = true
        }
    }

}
