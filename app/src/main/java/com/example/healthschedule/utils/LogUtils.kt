package com.example.healthschedule.utils

import android.os.Build
import android.util.Log
import com.example.healthschedule.BuildConfig

object LogUtils {
    private val TAG = "EXTRA_DEBUGGING_LOG"
    fun v (message : String) { if(BuildConfig.DEBUG) Log.i(TAG,message) }
    fun d (message : String) { if(BuildConfig.DEBUG) Log.i(TAG,message) }
    fun i (message : String) { if(BuildConfig.DEBUG) Log.i(TAG,message) }
    fun w (message : String) { if(BuildConfig.DEBUG) Log.i(TAG,message) }
    fun e (message : String) { if(BuildConfig.DEBUG) Log.i(TAG,message) }
    fun a (message : String) { if(BuildConfig.DEBUG) Log.i(TAG,message) }

}