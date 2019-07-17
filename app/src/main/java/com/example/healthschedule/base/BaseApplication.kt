package com.example.healthschedule.base

import android.app.Application
import android.widget.Toast
import javax.security.auth.callback.Callback

class BaseApplication : Application(), BaseContract {

    companion object {
        fun newInteance() : BaseApplication {
            return BaseApplication()
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun showToast(message: String) {

    }

    override fun hideToast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(title: String, message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessageListener(callback: BaseContract.MessageCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showKeyboard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideKeyboard() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}