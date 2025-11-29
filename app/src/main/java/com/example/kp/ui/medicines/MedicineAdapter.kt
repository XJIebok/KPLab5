package com.example.kp.ui.medicines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kp.R
import com.example.kp.medicineData

class MedicineAdapter(private val medicines: List<medicineData>) :
    RecyclerView.Adapter<MedicineAdapter.MedicineViewHolder>() {

        // Step 1: ViewHolder class
        class MedicineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val medicine: TextView = itemView.findViewById(R.id.medicine)
            val medicine_time: TextView = itemView.findViewById(R.id.medicine_time)
            val medicine_status: CheckBox = itemView.findViewById(R.id.medicine_status)
        }

        // Step 2: Inflate the item layout
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_of_medications, parent, false)
            return MedicineAdapter.MedicineViewHolder(view)
        }

        // Step 3: Bind data to the ViewHolder
        override fun onBindViewHolder(holder: MedicineAdapter.MedicineViewHolder, position: Int) {
            val currentItem = medicines[position]
            holder.medicine.text = currentItem.name
            holder.medicine_time.text = "${currentItem.hourTime}:${currentItem.minuteTime}"
            holder.medicine_status.isChecked = currentItem.status

            holder.medicine_status.setOnClickListener {
                currentItem.status = holder.medicine_status.isChecked
            }
        }

        // Step 4: the total number of items
        override fun getItemCount(): Int = medicines.size

}