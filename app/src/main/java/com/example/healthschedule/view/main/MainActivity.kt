package com.example.healthschedule.view.main

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.healthschedule.R
import com.example.healthschedule.adapter.page.PageAdapter
import com.example.healthschedule.adapter.page.PageTransformer
import com.example.healthschedule.adapter.workout.WorkoutAdapter
import com.example.healthschedule.data.source.WeeklyWorkoutRepository
import com.example.healthschedule.utils.*
import com.example.healthschedule.utils.ToastUtils.cancelToast
import com.example.healthschedule.utils.ToastUtils.showToast
import com.example.healthschedule.view.calendar.CalendarActivity
import com.example.healthschedule.view.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var presenter: MainPresenter
    private lateinit var pageAdapter: PageAdapter
    private lateinit var workoutAdapter: WorkoutAdapter

    private lateinit var fabOpen: Animation
    private lateinit var fabClose: Animation
    private lateinit var fabRotateOpen: Animation
    private lateinit var fabRotateClose: Animation

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
            view = this@MainActivity
            pageAdapterAdapterView = pageAdapter
            pagerAdapterModel = pageAdapter
            workoutAdapterView = workoutAdapter
            workoutAdapterModel = workoutAdapter
            weeklyWorkoutData = WeeklyWorkoutRepository
        }
        presenter.registrationWorkout(presenter.initWeekly()) // 운동 초기화




        val margin: Int = presenter.getViewPagerMargin(this)
        viewpager.setPageTransformer(true, PageTransformer()) // 뷰페이저 이동시 생기는 이펙트효과
        viewpager.offscreenPageLimit = 1
        viewpager.setPadding(margin * 5, margin, margin * 5, margin)
        viewpager.pageMargin = (margin / 2)
        viewpager.currentItem = DateUtils.day // 현재 요일


        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Toolbar의 왼쪽에 버튼을 추가한다.
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu) // 버튼의 아이콘을 변경한다.


        fabOpen = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open)
        fabClose = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close)
        fabRotateOpen = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_rotate_open)
        fabRotateClose = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_rotate_close)

        setOnClickListener()
    }
    override fun onResume() {
        super.onResume()
//        presenter.loadItems(DateUtils.day,false)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId) {
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

    override fun showToggle(isOpen: Boolean) {

        if (isOpen) {
            fab.startAnimation(fabRotateClose)
            fabSub1.startAnimation(fabClose)
            fabSub2.startAnimation(fabClose)
        } else {
            fab.startAnimation(fabRotateOpen)
            fabSub1.startAnimation(fabOpen)
            fabSub2.startAnimation(fabOpen)
        }
        fabSub1.isClickable = !isOpen
        fabSub2.isClickable = !isOpen
    }

    private fun setOnClickListener() {
//        presenter.loadItems(DateUtils.day,false)
        viewpager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                showToast("$position\n${DateUtils.getWeek()}\n" +
                        "${DateUtils.getDay(position)}\n" +
                        "${presenter.getItem(position)}")
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        // Floating 버튼 클릭 이벤트
        fab.setOnClickListener {
            showToast("확장")
            LogUtils.i(null,"확장!!")
            presenter.anim(it)
        }
        fabSub1.setOnClickListener {
            showToast("운동등록")
            presenter.anim(it)
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        fabSub2.setOnClickListener {
            presenter.anim(it)
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }
    }

}

