package com.example.kp



val medList = mutableListOf(
    medicineData(1, "Слабительное", 14, 30, true),
    medicineData(2, "Успокоительное", 14, 30, false),
    medicineData(3, "Жаропонижающее", 14, 30, false)
)
val medList2 = mutableListOf(
    medicineData(1, "Болеутоляющее", 14, 30, false),
    medicineData(2, "Успокоительное", 14, 30, false),
    medicineData(3, "Жаропонижающее", 14, 30, false)
)
val medList3 = mutableListOf(
    medicineData(1, "Слабительное", 14, 30, false),
    medicineData(2, "Успокоительное", 14, 30, false)
)

val humanLists = mutableListOf(
    humanData(1, "Чёрный", "Палата №1", R.drawable.human1, medList),
    humanData(2, "Красный", "Палата №2", R.drawable.human2, medList2),
    humanData(3, "Зелёный", "Палата №3", R.drawable.human3, medList3),
    humanData(4, "Голубой", "Палата №4", R.drawable.human4, medList2),
    humanData(5, "Фиолетовый", "Палата №5", R.drawable.human5, medList)
)