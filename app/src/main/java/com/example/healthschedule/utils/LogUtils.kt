package com.example.healthschedule.utils

import android.os.Build
import android.util.Log
import com.example.healthschedule.BuildConfig

object LogUtils {
    private val TAG = "EXTRA_DEBUGGING_LOG"
    fun d (tag : String? ,message : String) = if(BuildConfig.DEBUG and tag.isNullOrEmpty()) Log.d(TAG,message) else Log.d(tag,message)
    fun i (tag : String? ,message : String) = if(BuildConfig.DEBUG and tag.isNullOrEmpty()) Log.i(TAG,message) else Log.i(tag,message)
    fun w (tag : String? ,message : String) = if(BuildConfig.DEBUG and tag.isNullOrEmpty()) Log.w(TAG,message) else Log.w(tag,message)
    fun e (tag : String? ,message : String) = if(BuildConfig.DEBUG and tag.isNullOrEmpty()) Log.e(TAG,message) else Log.e(tag,message)
    fun v (tag : String? ,message : String) = if(BuildConfig.DEBUG and tag.isNullOrEmpty()) Log.v(TAG,message) else Log.v(tag,message)

}