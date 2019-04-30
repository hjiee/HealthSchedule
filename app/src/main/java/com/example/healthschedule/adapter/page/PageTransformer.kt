package com.example.healthschedule.adapter.page

import android.support.v4.view.ViewPager.PageTransformer
import android.util.Log
import android.view.View

/*******************************************************************
 * * * * *   * * * *   *     *       Created by OCN.Yang
 * *     *   *         * *   *       Time:2017/12/8 11:28.
 * *     *   *         *   * *       Email address:ocnyang@gmail.com
 * * * * *   * * * *   *     *.Yang  Web site:www.ocnyang.com
 */


class PageTransformer : PageTransformer {

    private val scaleMax = 0.8f
    private val alphaMax = 0.5f

    override fun transformPage(page: View, position: Float) {
//        page.translationX = horizontalPadding

        val scale =
            if (position < 0)
                (1 - scaleMax) * position + 1
            else
                (scaleMax - 1) * position + 1

        val alpha =
            if (position < 0)
                (1 - alphaMax) * position + 1
            else
                (alphaMax - 1) * position + 1

        if (position < 0) {
            page.pivotX = page.width.toFloat()
            page.pivotY = (page.height / 2).toFloat()
        } else {
            page.pivotX = 0f
            page.pivotY = (page.height / 2).toFloat()
        }
        page.scaleX = scale
        page.scaleY = scale
        Log.d("PagerTransformer","$position")
        Log.d("PagerTransformer","-----------------------------------")
        page.alpha = Math.abs(alpha) // 투명도
    }
}
