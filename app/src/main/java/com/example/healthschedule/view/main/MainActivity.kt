package com.example.healthschedule.view.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.healthschedule.R
import com.example.healthschedule.adapter.PageAdapter
import com.example.healthschedule.utils.DateUtils
import com.example.healthschedule.utils.LogUtils
import com.example.healthschedule.utils.ToastUtil
import com.example.healthschedule.utils.ToastUtils.Companion.cancelMessage
import com.example.healthschedule.utils.ToastUtils.Companion.showMessage
import com.example.healthschedule.view.calendar.CalendarActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    //    private val presenter : MainPresenter by lazy { MainPresenter() }
    private lateinit var presenter: MainPresenter
    private lateinit var pageAdapter: PageAdapter

    private lateinit var fabOpen: Animation
    private lateinit var fabClose: Animation
    private lateinit var fabRotateOpen: Animation
    private lateinit var fabRotateClose: Animation

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pageAdapter = PageAdapter(supportFragmentManager)
        presenter = MainPresenter().apply {
            view = this@MainActivity
            pageAdapterAdapterView = pageAdapter
            pagerAdapterModel = pageAdapter
        }
        setOnClickListener()


        val dpValue = 16
        val d = resources.displayMetrics.density
        val margin: Int = (dpValue * d).toInt()


        viewpager.adapter = pageAdapter
//        viewpager.setPageTransformer(true, PageTransformer())
        viewpager.offscreenPageLimit = 7
        viewpager.setPadding(margin * 5, margin, margin * 5, margin)
        viewpager.pageMargin = (margin / 2)
        viewpager.currentItem = DateUtils.day // 현재 요일



        fabOpen = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close)
        fabRotateOpen = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_rotate_open)
        fabRotateClose = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_rotate_close)

    }

    override fun onBackPressed() =
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            cancelMessage()
        }
        else {
            backPressedTime = System.currentTimeMillis()
            showMessage(resources.getString(R.string.BackPress))
        }



    override fun showToast(message: String) {
        showMessage(message)
//        Toast.makeText(this, "Message : $message", Toast.LENGTH_SHORT).show()
    }

    override fun showToggle(isOpen: Boolean) {
        if (isOpen) {
            fab.startAnimation(fabRotateClose)
            fabSub1.startAnimation(fabClose)
            fabSub2.startAnimation(fabClose)
            fabSub1.isClickable = false
            fabSub2.isClickable = false
//            fabSub1.hide()
//            fabSub2.hide()
        } else {
            fab.startAnimation(fabRotateOpen)
            fabSub1.startAnimation(fabOpen)
            fabSub2.startAnimation(fabOpen)
//            fabSub1.show()
//            fabSub2.show()
            fabSub1.isClickable = true
            fabSub2.isClickable = true
        }
    }

    fun setOnClickListener() {

        viewpager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                showMessage("${position.toString()}\n${DateUtils.getWeek()}\n${DateUtils.getDay(position)}")
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        // Floating 버튼 클릭 이벤트
        fab.setOnClickListener {
            presenter.anim(it)
//            showMessage("확장!")
            ToastUtil.showToast(this,"확장")
            LogUtils.i("확장!!")
        }
        fabSub1.setOnClickListener {
            ToastUtil.showToast(this,"운동등록")
            presenter.anim(it)
//            showMessage("운동등록")
//            val intent = Intent(this, CalendarActivity::class.java)
//            startActivity(intent)
        }
        fabSub2.setOnClickListener {
            presenter.anim(it)
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
    }


}

