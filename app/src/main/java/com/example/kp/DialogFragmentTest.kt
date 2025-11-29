//package com.example.kp
//
//import android.content.Context
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TimePicker
//import androidx.fragment.app.DialogFragment
//
//
//class DialogFragmentTest(position: Int) : DialogFragment() {
//
//    val position1 = position
//    private var observer: DialogFragmentObserver? = null
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        // Определение наблюдателя
//        if (context is DialogFragmentObserver)
//            observer = context
//        else
//            throw kotlin.Exception("$context не наблюдатель!")
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Привязка конкретного layout
//        return inflater.inflate(R.layout.medicine_add_layout, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val timePicker = view.findViewById<TimePicker>(R.id.medicine_time)
//        timePicker.setIs24HourView(true)
//        // Кнопка добавления лекарства в список назначенных
//        view.findViewById<Button>(R.id.save_medicine).setOnClickListener {
//            observer?.addNewMedicine(this, medicineData(
//                1,
//                view.findViewById<EditText>(R.id.title_editView).text.toString(),
//                timePicker.hour,
//                timePicker.minute,
//                false), position1)
//            this.dismiss()
//        }
//    }
//}
//interface DialogFragmentObserver {
//    fun addNewMedicine (dialog: DialogFragmentTest, newMedicine: medicineData, position: Int)
//}