package com.example.healthschedule.utils

import android.content.Context
import android.widget.Toast

object ToastUtils {
    lateinit var toast: Toast
    fun Context?.showToast(message: String) {
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun cancelToast() = toast.cancel()

}