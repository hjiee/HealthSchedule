package com.example.healthschedule.view.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.healthschedule.R
import kotlinx.android.synthetic.main.registration_dialog.*
import kotlinx.android.synthetic.main.registration_dialog.view.*
import kotlinx.android.synthetic.main.registration_dialog.view.btn_ok

class RegistrationDialogFragment : DialogFragment(){

    companion object {
        fun newInstance() : RegistrationDialogFragment {
            return RegistrationDialogFragment()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.registration_dialog,container,false)

        view.tv_week.text = tag

        view.btn_ok.setOnClickListener {  }
        view.btn_cancel.setOnClickListener { dismiss() }
        return view
    }
}