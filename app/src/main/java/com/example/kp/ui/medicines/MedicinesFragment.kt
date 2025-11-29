package com.example.kp.ui.medicines

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kp.api.RetrofitInstance
import com.example.kp.api.hasInternetConnection
import com.example.kp.databinding.FragmentMedicinesBinding
import kotlinx.coroutines.launch

class MedicinesFragment: Fragment(), MedicineRecyclerViewObserver{
    private var _binding: FragmentMedicinesBinding? = null
    private val me = this

    // GPT START
    private lateinit var etIngredient: EditText
    private lateinit var btnSearch: Button
    private lateinit var tvStatus: TextView
    //private lateinit var tvResults: TextView
    private lateinit var medicinesRecyclerview: RecyclerView
    // GPT END

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(MedicinesViewModel::class.java)

        _binding = FragmentMedicinesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        ///GPT START
        etIngredient = binding.etIngredient
        btnSearch = binding.btnSearch
        tvStatus = binding.tvStatus
        medicinesRecyclerview = binding.medicinesRecyclerview
        btnSearch.setOnClickListener {
            try {
                medicinesRecyclerview.adapter = null
                medicinesRecyclerview.adapter?.notifyDataSetChanged()
                searchDrugs()

            } catch (e: Exception) {btnSearch.text = e.toString()}
            //searchDrugs()
        }
        // GPT END

//        val textView: TextView = binding.textMedicines
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun searchDrugs() {
        val ingredient = etIngredient.text.toString().trim()

        if (ingredient.isEmpty()) {
            Toast.makeText(requireContext(), "Введите действующее вещество", Toast.LENGTH_SHORT).show()
            return
        }

        // 1. Проверяем интернет
        if (!hasInternetConnection(requireContext())) {
            tvStatus.text = "Статус: нет интернет-соединения"
            //tvResults.text = ""
            Toast.makeText(requireContext(), "Нет интернета, проверьте подключение", Toast.LENGTH_SHORT).show()
            return
        }


        tvStatus.text = "Статус: загружаем данные..."
        //tvResults.text = ""

        // 2. Сетевой запрос — корутина, привязана к viewLifecycleOwner
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val searchQuery = "active_ingredient:\"$ingredient\""

                val response = RetrofitInstance.api.searchByActiveIngredient(
                    search = searchQuery,
                    limit = 10
                )

                if (response.isSuccessful) {
                    val body = response.body()
                    val results = body?.results

                    if (results.isNullOrEmpty()) {
                        tvStatus.text = "Статус: ничего не найдено"
                        //tvResults.text = "Нет лекарств с действующим веществом: $ingredient"
                    } else {
                        tvStatus.text = "Статус: найдено ${results.size} вариантов"

                        val text = results.joinToString("\n\n") { item ->
                            val brands = item.openfda?.brand_name?.joinToString(", \n")
                                ?: "Без торгового названия"
                            val actives = item.active_ingredient?.joinToString(", \n")
                                ?: "Нет данных о дозировке"
                            val manufacturer =
                                item.openfda?.manufacturer_name?.joinToString(", \n")
                                    ?: "Не указан производитель"

                            """
                            Торговое название: $brands
                            Действующее вещество: $actives
                            Производитель: $manufacturer
                            """.trimIndent()
                        }

                        medicinesRecyclerview.adapter = MedicinesAdapter(results, me)
                        medicinesRecyclerview.layoutManager = LinearLayoutManager(context)

                        //tvResults.text = ""
                    }
                } else {
                    tvStatus.text = "Статус: ошибка сервера (${response.code()})"
                    //tvResults.text = ""
                }
            } catch (e: Exception) {
                tvStatus.text = "Статус: ошибка при запросе"
                //tvResults.text = ""
                Toast.makeText(
                    requireContext(),
                    "Ошибка: ${e.localizedMessage ?: "неизвестная"}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    private fun copyTextToClipboard(text: String) {
        // requireContext() гарантирует, что контекст не null, пока фрагмент присоединен.
        val clipboardManager = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        // Создаем объект ClipData с простым текстом
        val clipData = ClipData.newPlainText("Copied Text Fragment", text)

        // Устанавливаем данные в буфер обмена
        clipboardManager.setPrimaryClip(clipData)

        // Показываем Toast-уведомление пользователю
        // Используем requireContext() или activity для Toast
        Toast.makeText(requireContext(), "Текст скопирован: $text", Toast.LENGTH_SHORT).show()
    }

    override fun Clipboard(text: String) {
        copyTextToClipboard(text)
    }
}
