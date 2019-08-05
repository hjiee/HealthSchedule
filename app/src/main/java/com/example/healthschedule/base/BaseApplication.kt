package com.example.healthschedule.base

import android.app.Application
import android.widget.Toast
import javax.security.auth.callback.Callback

class BaseApplication : Application() {

    companion object {
        fun newInteance() : BaseApplication {
            return BaseApplication()
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

}