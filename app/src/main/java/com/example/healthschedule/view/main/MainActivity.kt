package com.example.healthschedule.view.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import com.example.healthschedule.R
import com.example.healthschedule.adapter.page.PageAdapter
import com.example.healthschedule.adapter.workout.WorkoutAdapter
import com.example.healthschedule.base.BaseActivity
import com.example.healthschedule.data.source.WeeklyWorkoutRepository
import com.example.healthschedule.utils.CommonDefine.Companion.REQUEST_CODE_CALENDAR
import com.example.healthschedule.utils.CommonDefine.Companion.REQUEST_CODE_REGISTRATION
import com.example.healthschedule.utils.DateUtils
import com.example.healthschedule.utils.LogUtils
import com.example.healthschedule.utils.ToastUtils.cancelToast
import com.example.healthschedule.utils.ToastUtils.showToast
import com.example.healthschedule.view.calendar.CalendarActivity
import com.example.healthschedule.view.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_weekly_workout.view.*
import java.util.*

class MainActivity : BaseActivity(), MainContract.View {
    private lateinit var presenter: MainPresenter
    private lateinit var pageAdapter: PageAdapter
    private lateinit var workoutAdapter: WorkoutAdapter

    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // ViewPager 어댑터
        pageAdapter = PageAdapter(supportFragmentManager)
        viewpager.adapter = pageAdapter

        // Weekly Workout 어댑터
        workoutAdapter = WorkoutAdapter()
        recycler.adapter = workoutAdapter

        presenter = MainPresenter().apply {
            mainView = this@MainActivity
            pageAdapterAdapterView = pageAdapter
            pagerAdapterModel = pageAdapter
            workoutAdapterView = workoutAdapter
            workoutAdapterModel = workoutAdapter
            weeklyWorkoutData = WeeklyWorkoutRepository
        }
        presenter.registrationWorkout(presenter.initWeekly()) // 운동 초기화


        val margin: Int = presenter.getViewPagerMargin(this)
//        viewpager.setPageTransformer(true, PageTransformer()) // 뷰페이저 이동시 생기는 이펙트효과
        viewpager.offscreenPageLimit = 4
        viewpager.setPadding(margin * 5, margin, margin * 5, margin)
        viewpager.pageMargin = (margin / 2)
        viewpager.currentItem = DateUtils.week // 현재 요일


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Toolbar의 왼쪽에 버튼을 추가한다.
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu) // 버튼의 아이콘을 변경한다.

        setOnClickListener()
    }

    override fun onResume() {
        super.onResume()
//        presenter.loadItems(DateUtils.day,false)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (presenter.isButtonExtended) {
            presenter.anim()
        }
        return super.onTouchEvent(event)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        showToast(requestCode.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> showToast(item.toString())
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() =
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            cancelToast()
        } else {
            backPressedTime = System.currentTimeMillis()
            showToast(resources.getString(R.string.BackPress))
        }

    override fun showSubFab(fabAction: Animation, fabRotateAction: Animation) {
        fab.startAnimation(fabRotateAction)
        fabSub1.startAnimation(fabAction)
        fabSub2.startAnimation(fabAction)
        tv_registration.startAnimation(fabAction)
        tv_calendar.startAnimation(fabAction)

    }

    override fun hideSubFab(fabAction: Animation, fabRotateAction: Animation) {
        fab.startAnimation(fabRotateAction)
        fabSub1.startAnimation(fabAction)
        fabSub2.startAnimation(fabAction)
        tv_registration.startAnimation(fabAction)
        tv_calendar.startAnimation(fabAction)
    }

    override fun isExpanded(state: Boolean) {
        fabSub1.isClickable = state
        fabSub2.isClickable = state
        presenter.isButtonExtended = state
    }

    private fun setOnClickListener() {
        var count = 0;
        var moveStart = 0f;
        var moveEnd = 0f;
//        presenter.loadItems(DateUtils.day,false)
        viewpager.setOnTouchListener { v, event ->
                        Log.e("viewpager",event.toString())

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    moveStart = event.x
                    false
                }
                MotionEvent.ACTION_UP -> {
                    Log.e("viewpager", count.toString())
                    moveEnd = event.x
                    var distance = Math.abs(moveStart - moveEnd)
                    Log.e("viewpager", distance.toString())
                    if (count < 7 && distance < 100) v.performClick()
                    count = 0
                    false
                }
                else -> {
                    count++
                    false
                }
            }
        }
        viewpager.setOnClickListener {
//            showToast("${DateUtils.year}년 ${DateUtils.month}월 ${DateUtils.day}일 ${DateUtils.getWeek(viewpager.currentItem)}")
            showToast("${DateUtils.getDate(DateUtils.week, viewpager.currentItem)}")
        }


        // 운동 페이지가 움직일때 이벤트 처리
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
//                Log.e("LifeTest", "currentPosition : $position")
//                Log.e("LifeTest", "currentItem : ${viewpager.currentItem}")

//                showToast(
//                    "$position\n${DateUtils.getWeek()}\n" +
//                            "${DateUtils.getDay(position)}\n" +
//                            "${presenter.getItem(position)}"
//                )
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        // Floating 버튼 클릭 이벤트
        fab.setOnClickListener {
            showToast("확장")
            LogUtils.i(null, "확장!!")
            presenter.anim()
        }
        fabSub1.setOnClickListener {
            showToast("운동등록")
            presenter.anim()
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_REGISTRATION)
//            startActivity(intent)
        }
        fabSub2.setOnClickListener {
            presenter.anim()
            val intent = Intent(this, CalendarActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_CALENDAR)
//            startActivity(intent)
        }
    }


}

