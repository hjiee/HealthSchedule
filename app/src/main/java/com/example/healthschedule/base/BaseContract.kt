package com.example.healthschedule.base

import javax.security.auth.callback.Callback

interface BaseContract {
    /**
     * 토스트 메시지를 표시/숨기기한다.
     */
    fun showToast(message : String)
    fun hideToast()

    /**
     * 메시지 다이얼로그를 표시한다.
     */
    interface MessageCallback {
        fun onResult()
    }
    fun showMessage(message : String)
    fun showMessage(title : String, message : String)
    fun showMessageListener(callback : MessageCallback)


    /**
     * 비동기 처리시 프로그레스를 표시/숨기기 한다.
     */
    fun showProgress()
    fun dismissProgress()

    /**
     * 키보드 입력창을 표시/숨기기 한다
     */
    fun showKeyboard()
    fun hideKeyboard()
}