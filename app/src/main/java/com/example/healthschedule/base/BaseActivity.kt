package com.example.healthschedule.base

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.healthschedule.R
import com.example.healthschedule.utils.ToastUtils
import com.example.healthschedule.utils.ToastUtils.showToast

open class BaseActivity : AppCompatActivity() {
    private var backPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onBackPressed() {
        super.onBackPressed()
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
        alertDialog.setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
            showToast("등록")
            finish()
        })
        alertDialog.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which -> showToast("취소") })
        alertDialog.show()
//            showToast("등록")
//            finish()
    }
}