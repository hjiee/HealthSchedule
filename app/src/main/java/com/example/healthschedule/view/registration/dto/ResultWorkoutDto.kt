package com.example.healthschedule.view.registration.dto

data class ResultWorkoutDto(
    var day: String,
    var items: MutableList<EachWorkoutDto>
) {

}