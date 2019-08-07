package com.example.healthschedule.view.registration.dto

import java.util.*

data class ResultWorkoutDto(
    var date : String,
    var position : Int,
    var workouts: MutableList<EachWorkoutDto?>?
) {

}