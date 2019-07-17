package com.example.healthschedule.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.animation.Animation
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import com.example.healthschedule.R
import com.example.healthschedule.adapter.page.PageAdapter
import com.example.healthschedule.adapter.workout.WorkoutAdapter
import com.example.healthschedule.base.BaseActivity
import com.example.healthschedule.data.source.WeeklyWorkoutRepository
import com.example.healthschedule.utils.CommonDefine.Companion.REQUEST_CODE_CALENDAR
import com.example.healthschedule.utils.CommonDefine.Companion.REQUEST_CODE_REGISTRATION
import com.example.healthschedule.utils.DateUtils.getDate
import com.example.healthschedule.utils.DateUtils.getTodayPosition
import com.example.healthschedule.utils.ToastUtils.cancelToast
import com.example.healthschedule.utils.ToastUtils.showToast
import com.example.healthschedule.view.calendar.CalendarActivity
import com.example.healthschedule.view.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.View {
    private lateinit var presenter: MainPresenter
    private lateinit var pageAdapter: PageAdapter
    private lateinit var workoutAdapter: WorkoutAdapter


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
//        presenter.registrationWorkout(presenter.initWeekly()) // 운동 초기화


        val margin: Int = presenter.getViewPagerMargin(this)
//        viewpager.setPageTransformer(true, PageTransformer()) // 뷰페이저 이동시 생기는 이펙트효과
        viewpager.offscreenPageLimit = 4
        viewpager.setPadding(margin * 5, margin, margin * 5, margin)
        viewpager.pageMargin = (margin / 2)
        viewpager.currentItem = getTodayPosition().let {
            when(it) {
                -1 -> 6
                else -> it
            }
        }
//            if(getTodayPosition()==-1) 6 else getTodayPosition() // 현재 요일


        setSupportActionBar(toolbar)
        ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close).let {
            drawer.addDrawerListener(it)
            it.syncState()
            it.drawerArrowDrawable = DrawerArrowDrawable(this).apply {
                color = resources.getColor(R.color.colorwhite,null)
            }
        }
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
//        showToast(requestCode.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> showToast(item.toString())
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() = super.onBackPressed()

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
//            showToast("${DateUtils.getDate(DateUtils.week, viewpager.currentItem)}")
            showToast("${getDate(viewpager.currentItem)}")
        }



        // Floating 버튼 클릭 이벤트
        fab.setOnClickListener {
            presenter.anim()
        }
        fabSub1.setOnClickListener {
            presenter.anim()
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_REGISTRATION)
        }
        fabSub2.setOnClickListener {
            presenter.anim()
            val intent = Intent(this, CalendarActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_CALENDAR)
        }
    }


}

