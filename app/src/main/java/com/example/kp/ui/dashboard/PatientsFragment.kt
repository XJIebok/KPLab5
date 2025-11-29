package com.example.kp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kp.DialogFragmentObserver
import com.example.kp.Dialog_Fragment
import com.example.kp.HumanAdapter
import com.example.kp.databinding.FragmentPatients2Binding
import com.example.kp.humanLists
import com.example.kp.medicineData

//import com.example.kp.humanLists

class PatientsFragment : Fragment(), DialogFragmentObserver {

    private var _binding: FragmentPatients2Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

//    private lateinit var nameTextView: TextView
//    private lateinit var chamberTextView: TextView
//    private lateinit var photoImageButton: ImageButton
//    private lateinit var medicineRecyclerview: RecyclerView
//    private lateinit var addMedicine: Button
//    private lateinit var deleteMedicine: Button
    private lateinit var patientsRecyclerview: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val patientsViewModel =
            ViewModelProvider(this).get(PatientsViewModel::class.java)

        _binding = FragmentPatients2Binding.inflate(inflater, container, false)
        val root: View = binding.root



//        nameTextView = binding.nameTextView
//        chamberTextView = binding.chamberTextView
//        photoImageButton = binding.photoImageButton
//        medicineRecyclerview = binding.medicineRecyclerview
//        addMedicine = binding.addMedicine
//        deleteMedicine = binding.deleteMedicine


        patientsRecyclerview = binding.patientsRecyclerview
        patientsRecyclerview.adapter = HumanAdapter(humanLists)
        patientsRecyclerview.layoutManager = LinearLayoutManager(context)

//        val textView: TextView = binding.textDashboard
//        patientsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun addNewMedicine(
        dialog: Dialog_Fragment,
        newMedicine: medicineData,
        position: Int
    ) {
        humanLists[position].medicine_list.add(newMedicine)
        patientsRecyclerview.adapter = HumanAdapter(humanLists)
    }
}