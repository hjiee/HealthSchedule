package com.example.healthschedule.view.registration

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.healthschedule.R
import com.example.healthschedule.adapter.registration.RegistrationAdapter
import com.example.healthschedule.adapter.registration.SwipeToDeleteCallback
import com.example.healthschedule.view.registration.dto.WorkoutDto
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_registration.*
import kotlinx.android.synthetic.main.dialog_registration.view.*
import kotlinx.android.synthetic.main.dialog_registration.view.recycler_workout
import kotlinx.android.synthetic.main.expansion_panel_sample_panel.*
import kotlinx.android.synthetic.main.include_bottom_layout.view.btn_cancel
import kotlinx.android.synthetic.main.include_bottom_layout.view.btn_ok


class RegistrationDialogFragment : DialogFragment() {

    var registrationCallback : DailyWorkoutCallback? = null
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
//        val layoutParams = dialog.window.attributes
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
//        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
//        dialog.window.attributes = layoutParams



        val adapter = RegistrationAdapter((1..3).map { "Item: $it" }.toMutableList())
        view.recycler_workout.adapter = adapter

        // 스와이프로 리사이클러뷰의 아이템을 삭제한다.
        val swipHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipHandler)
        itemTouchHelper.attachToRecyclerView(view.recycler_workout)


        var count = 0
        view.tv_week.text = tag
        view.btn_add.setOnClickListener {
            adapter.addItem("Item: ${count++}")
        }


        // Ok Cancel
        view.findViewById<View>(R.id.include_bottom_layout).run {
            btn_ok.setOnClickListener {
                registrationCallback?.result()
                dismiss()
            }
            btn_cancel.setOnClickListener { dismiss() }
        }
        view.findViewById<ImageView>(R.id.img_all_delete).run {
            setOnClickListener { adapter.removeAll() }
        }


        return view
    }

    interface DailyWorkoutCallback {
        fun result()
    }
    fun setDailyWorkoutCallback(callback : DailyWorkoutCallback) {
        registrationCallback = callback
    }
}