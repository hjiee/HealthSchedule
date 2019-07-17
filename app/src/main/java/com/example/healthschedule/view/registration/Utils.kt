package com.example.healthschedule.view.registration

import android.content.Context
import android.util.DisplayMetrics

object Utils {
    fun dpToPx(context: Context?, dp: Float): Int {
        return (dp * (context?.resources?.displayMetrics?.densityDpi?.toFloat() ?: 0f / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    fun pxToDp(context: Context?, px: Float): Float {
        return px / (context?.resources?.displayMetrics?.densityDpi?.toFloat() ?: 0f / DisplayMetrics.DENSITY_DEFAULT)
    }
}
