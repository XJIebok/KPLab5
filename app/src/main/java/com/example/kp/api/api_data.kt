package com.example.kp.api


// OpenFdaModels.kt
data class DrugLabelResponse(
    val results: List<DrugLabel>?
)

// Один элемент массива results
data class DrugLabel(
    val openfda: OpenFdaInfo?,
    val active_ingredient: List<String>?
)

// Вложенный объект с брендами и производителем
data class OpenFdaInfo(
    val brand_name: List<String>?,          // торговые названия (Advil, Motrin...)
    val manufacturer_name: List<String>?    // производитель
)

