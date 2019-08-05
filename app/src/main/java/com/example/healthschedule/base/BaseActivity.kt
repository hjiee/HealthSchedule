package com.example.healthschedule.base

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.healthschedule.R
import com.example.healthschedule.utils.ToastUtils
import com.example.healthschedule.utils.ToastUtils.showToast

open class BaseActivity : AppCompatActivity(), BaseContract.View {
    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            ToastUtils.cancelToast()
        } else {
            backPressedTime = System.currentTimeMillis()
            showToast(resources.getString(R.string.BackPress))
        }
    }

    fun showAlterDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage("등록하시겠습니까?")
        alertDialog.setPositiveButton("Ok") { _, _ ->
            showToast("등록")
            finish()
        }
        alertDialog.setNegativeButton("Cancel") { _, _ ->
            showToast("취소")
        }
        alertDialog.show()
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
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

    override fun showMessageListener(callback: BaseContract.View.MessageCallback) {
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