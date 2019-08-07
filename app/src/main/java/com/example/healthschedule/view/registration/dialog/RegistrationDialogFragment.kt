package com.example.healthschedule.view.registration.dialog

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.healthschedule.R
import com.example.healthschedule.adapter.registration.RegistrationAdapter
import com.example.healthschedule.adapter.registration.SwipeToDeleteCallback
import com.example.healthschedule.utils.DateUtils.getDate
import com.example.healthschedule.view.registration.dto.EachWorkoutDto
import com.example.healthschedule.view.registration.dto.ResultWorkoutDto
import kotlinx.android.synthetic.main.dialog_registration.view.*
import kotlinx.android.synthetic.main.dialog_registration.view.recycler_workout
import kotlinx.android.synthetic.main.include_bottom_layout.view.btn_cancel
import kotlinx.android.synthetic.main.include_bottom_layout.view.btn_ok


class RegistrationDialogFragment : DialogFragment() {

    var registrationCallback: DailyWorkoutCallback? = null

    companion object {
        fun newInstance(): RegistrationDialogFragment {
            return RegistrationDialogFragment()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Material_Light)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_registration, container, false)


//        val adapter = RegistrationAdapter((1 downTo 1).map { EachWorkoutDto(it,"$it", "$it") }.toMutableList())
        val adapter = RegistrationAdapter(mutableListOf())
        view.recycler_workout.adapter = adapter
        // 스와이프로 리사이클러뷰의 아이템을 삭제한다.
        val swipHandler = object : SwipeToDeleteCallback(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipHandler)
        itemTouchHelper.attachToRecyclerView(view.recycler_workout)


        // 아이템을 추가한다.
        view.tv_week.text = tag
        view.btn_add.setOnClickListener {
            adapter.addItem(EachWorkoutDto(0, "어깨", "${adapter.getItemCount() + 1}")) // 아이템 추가
        }

        // 추가된 아이템을 모두 삭제한다.
        view.findViewById<ImageView>(R.id.img_all_delete).run {
            setOnClickListener { adapter.removeAll() }
        }

        // 추가된 아이템을 담는다.
        view.findViewById<View>(R.id.include_bottom_layout).run {
            btn_ok.setOnClickListener {
                if (adapter.itemCount > 0) {
                    view.tv_week.text.toString().let { day ->
                        ResultWorkoutDto(
                            getDate(com.example.healthschedule.utils.DateUtils.getPosion(day)),
                            com.example.healthschedule.utils.DateUtils.getPosion(day),
                            adapter.getItem()
                        ).let { result -> registrationCallback?.result(result) }
                    }

                    dismiss()
                } else {
                    Toast.makeText(context, "추가된 운동이 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
            btn_cancel.setOnClickListener { dismiss() }
        }




        return view
    }

    interface DailyWorkoutCallback {
        fun result(resultWorkoutDto: ResultWorkoutDto)
    }

    fun setDailyWorkoutCallback(callback: DailyWorkoutCallback) {
        registrationCallback = callback
    }
}