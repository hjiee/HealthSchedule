package com.example.healthschedule.base

import android.app.Application

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    fun showToast() {

    }
    fun stopToast() {

    }

    fun showMessage(message : String) {

    }
}