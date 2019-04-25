package com.example.healthschedule.utils

import android.content.Context
import android.widget.Toast

object ToastUtil {
    lateinit var toast: Toast
    fun showToast(context : Context , message: String) {
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    fun cancelToast() = toast.cancel()

}