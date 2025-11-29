package com.example.kp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kp.ui.medicines.MedicineAdapter

class HumanAdapter(private val humans: List<humanData>) :
    RecyclerView.Adapter<HumanAdapter.HumanViewHolder>() {
    var tempPositon: Int = 0

    class HumanViewHolder(humanView: View): RecyclerView.ViewHolder(humanView) {
        val nameTextView = humanView.findViewById<TextView>(R.id.nameTextView)
        val chamberTextView = humanView.findViewById<TextView>(R.id.chamberTextView)
        val photoImageButton = humanView.findViewById<ImageButton>(R.id.photoImageButton)
        val medicineRecyclerView = humanView.findViewById<RecyclerView>(R.id.medicine_recyclerview)
        val addMedicineButton = humanView.findViewById<Button>(R.id.add_medicine)
        val deleteMedicineButton = humanView.findViewById<Button>(R.id.delete_medicine)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HumanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.patients_layout, parent,false)
        return HumanViewHolder(view)
    }

    override fun onBindViewHolder(holder: HumanViewHolder, position: Int) {
        val currentItem = humans[position]
        holder.nameTextView.text = currentItem.name
        holder.chamberTextView.text = currentItem.description
        holder.photoImageButton.setImageResource(currentItem.photo)
        val context = holder.medicineRecyclerView.context
        holder.medicineRecyclerView.layoutManager = LinearLayoutManager(context)
        holder.medicineRecyclerView.adapter = MedicineAdapter(currentItem.medicine_list)

        holder.addMedicineButton.setOnClickListener {
            val dialogFragment = Dialog_Fragment(position)
            val activity = context as FragmentActivity
            try { dialogFragment.show(activity.supportFragmentManager, "medicine_add_dialog_tag")
            }
            catch (e: Exception) { holder.addMedicineButton.text = e.toString() }
            tempPositon = position
        }
        holder.deleteMedicineButton.setOnClickListener {
            showConfirmationDialog(context, "Вы уверены, что хотите удалить выбранные лекарства?") { isConfirmed ->
                if (isConfirmed) {
                    val deleteList = mutableListOf<Int>()
                    currentItem.medicine_list.forEachIndexed { index, it ->
                        if (it.status) {
                            deleteList.add(0, index)
                        }
                    }
                    deleteList.forEach {
                        currentItem.medicine_list.removeAt(it)
                    }
                    holder.medicineRecyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }
    }
    override fun getItemCount(): Int = humans.size
}