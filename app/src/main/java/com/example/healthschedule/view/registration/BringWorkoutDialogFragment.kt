package com.example.healthschedule.view.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_bring.view.*
import kotlinx.android.synthetic.main.include_bottom_layout.view.btn_cancel
import kotlinx.android.synthetic.main.include_bottom_layout.view.btn_ok



class BringWorkoutDialogFragment : DialogFragment(){

    companion object {
        fun newInstance() : BringWorkoutDialogFragment {
            return BringWorkoutDialogFragment()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(com.example.healthschedule.R.layout.dialog_bring,container,false)
        view.findViewById<View>(com.example.healthschedule.R.id.layout_button).let {
            it.btn_ok.setOnClickListener { showToast(view.group_radio) }
            it.btn_cancel.setOnClickListener { dismiss() }
        }
        return view
    }

    fun showToast(radioButtonGroup : RadioGroup?) {
        val radioButton = radioButtonGroup?.findViewById<RadioButton>(radioButtonGroup.checkedRadioButtonId)
        val idx = radioButtonGroup?.indexOfChild(radioButton)
        val btnCheckedRadio = radioButtonGroup?.getChildAt(idx?:0) as RadioButton?
        val checkedText = btnCheckedRadio?.text.toString()
        Toast.makeText(context,checkedText,Toast.LENGTH_SHORT).show()
    }
}