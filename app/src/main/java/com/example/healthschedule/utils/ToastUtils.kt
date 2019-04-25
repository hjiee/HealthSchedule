package com.example.healthschedule.utils

import android.content.Context
import android.widget.Toast

class ToastUtils constructor(context: Context) {
    val toast = Toast(context)
    fun show() = toast.show()
    fun cancel() = toast.cancel()

    companion object {
        lateinit var toast: Toast
        fun Context.showMessage(message: CharSequence) {
            toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
            toast.show()
        }

        fun cancelMessage() = toast.cancel()
    }
}
