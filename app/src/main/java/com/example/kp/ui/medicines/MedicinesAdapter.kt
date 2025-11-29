package com.example.kp.ui.medicines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kp.R
import com.example.kp.api.DrugLabel


class MedicinesAdapter(private val medicines: List<DrugLabel>?,
                       private val observer: MedicineRecyclerViewObserver) :
    RecyclerView.Adapter<MedicinesAdapter.MedicinesViewHolder>() {

    // Step 1: ViewHolder class
    class MedicinesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val medicine_name: TextView = itemView.findViewById(R.id.medicine_name_textview)
        val active_substance: TextView = itemView.findViewById(R.id.active_substance_textview)
        val clipboard: ImageButton = itemView.findViewById(R.id.clipboard_button)
        val description: TextView = itemView.findViewById(R.id.description_textview)
    }

    // Step 2: Inflate the item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicinesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_menicines, parent, false)
        return MedicinesAdapter.MedicinesViewHolder(view)
    }

    // Step 3: Bind data to the ViewHolder
    override fun onBindViewHolder(holder: MedicinesAdapter.MedicinesViewHolder, position: Int) {
        val currentItem = medicines!![position]
        holder.medicine_name.text = currentItem.openfda?.brand_name.toString()
        holder.active_substance.text = currentItem.active_ingredient?.joinToString(", \n")
            ?: "Нет данных о дозировке"
        holder.description.text = currentItem.openfda?.manufacturer_name?.joinToString(", \n")
            ?: "Не указан производитель"

        holder.clipboard.setOnClickListener {
            observer.Clipboard(holder.medicine_name.text.toString())
        }
    }
    // Step 4: the total number of items
    override fun getItemCount(): Int = medicines!!.size

}
interface MedicineRecyclerViewObserver{
    fun Clipboard(text: String)
}