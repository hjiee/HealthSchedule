package com.example.healthschedule.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.animation.Animation
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.view.GravityCompat
import com.example.healthschedule.R
import com.example.healthschedule.adapter.page.PageAdapter
import com.example.healthschedule.adapter.workout.WorkoutAdapter
import com.example.healthschedule.base.BaseActivity
import com.example.healthschedule.data.source.WeeklyWorkoutRepository
import com.example.healthschedule.utils.CommonUtils.Companion.REQUEST_CODE_CALENDAR
import com.example.healthschedule.utils.CommonUtils.Companion.REQUEST_CODE_REGISTRATION
import com.example.healthschedule.utils.DateUtils.getDate
import com.example.healthschedule.utils.DateUtils.getTodayPosition
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


//        viewpager.setPageTransformer(true, PageTransformer()) // 뷰페이저 이동시 생기는 이펙트효과
        val margin: Int = presenter.getViewPagerMargin(this)
        viewpager.offscreenPageLimit = 4
        viewpager.setPadding(margin * 5, margin, margin * 5, margin)
        viewpager.pageMargin = (margin / 2)
        // 현재 날짜에 맞는 뷰페이저를 선택한다.
        viewpager.currentItem = getTodayPosition().let {
            when (it) {
                -1 -> 6
                else -> it
            }
        }



        setSupportActionBar(toolbar)
        ActionBarDrawerToggle(
            this,
            drawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ).let {
            drawer.addDrawerListener(it)
            it.syncState()
            it.drawerArrowDrawable = DrawerArrowDrawable(this).apply {
                color = resources.getColor(R.color.colorwhite, null)
            }
        }
        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_profile -> showToast(it.title.toString())
                R.id.nav_statistics -> showToast(it.title.toString())
                R.id.nav_board -> showToast(it.title.toString())
                R.id.nav_motivation -> showToast(it.title.toString())
                R.id.nav_contact -> showToast(it.title.toString())
                R.id.nav_star -> showToast(it.title.toString())
                R.id.nav_help -> showToast(it.title.toString())
                R.id.nav_opensource -> showToast(it.title.toString())
                R.id.nav_setting -> showToast(it.title.toString())
                R.id.nav_logout -> showToast(it.title.toString())
                else -> ""
            }
            drawer.closeDrawer(GravityCompat.START)
            true
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
        when (resultCode) {
            REQUEST_CODE_REGISTRATION -> {
                //TODO 파이어베이스 디비에 들어있는 데이터들을 로드하여 표시하기

            }
            REQUEST_CODE_CALENDAR -> ""
        }
//        showToast(requestCode.toString())
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> showToast(item.toString())
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        presenter.animOff()
        when (drawer.isDrawerOpen(GravityCompat.START)) {
            true -> {
                drawer.closeDrawer(GravityCompat.START)
            }
            false -> {
                super.onBackPressed()
            }
        }

    }


    override fun actionFab(fabAction: Animation, fabRotateAction: Animation) {
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
        var count = 0
        var moveStart = 0f
        var moveEnd = 0f

        viewpager.setOnTouchListener { v, event ->
            Log.e("viewpager", event.toString())

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
        // 운동 등록
        fabSub1.setOnClickListener {
            presenter.anim()
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_REGISTRATION)
        }
        // 운동달력
        fabSub2.setOnClickListener {
            presenter.anim()
            val intent = Intent(this, CalendarActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_CALENDAR)
        }
    }


}

