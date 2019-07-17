package com.example.healthschedule.view.registration

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.healthschedule.R
import com.example.healthschedule.adapter.registration.RegistrationAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_registration.*
import kotlinx.android.synthetic.main.dialog_registration.view.*
import kotlinx.android.synthetic.main.dialog_registration.view.recycler_workout
import kotlinx.android.synthetic.main.include_bottom_layout.view.btn_cancel
import kotlinx.android.synthetic.main.include_bottom_layout.view.btn_ok



class RegistrationDialogFragment : DialogFragment() {

    companion object {
        fun newInstance(): RegistrationDialogFragment {
            return RegistrationDialogFragment()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Material_Light)
//        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        setStyle(STYLE_NO_TITLE, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_registration, container, false)
        val layoutParams = dialog.window.attributes
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog.window.attributes = layoutParams


        // Ok Cancel
        view.findViewById<View>(R.id.include_bottom_layout).let {
            it.btn_ok.setOnClickListener { }
            it.btn_cancel.setOnClickListener { dismiss() }
        }


        val list = arrayListOf<String>()
        val adapter = RegistrationAdapter(list)
        view.recycler_workout.adapter = adapter


        var count = 0
        view.tv_week.text = tag
        view.btn_add.setOnClickListener {
            list.add(count++.toString())
            adapter.notifyDataSetChanged()
        }

        return view
    }
}