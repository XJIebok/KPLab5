package com.example.kp


data class humanData(
    val id: Int,
    var name: String,
    val description: String,
    val photo: Int,
    val medicine_list: MutableList<medicineData>
)
