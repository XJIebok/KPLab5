package com.example.kp

import android.R
import android.content.Context
import androidx.appcompat.app.AlertDialog

fun showConfirmationDialog(context: Context, message: String, onResult: (Boolean) -> Unit) {
    AlertDialog.Builder(context)
        .setTitle("Подтверждение действия") // Заголовок диалога
        .setMessage(message) // Сообщение с вопросом
        .setPositiveButton("Да") { dialog, which ->
            // Пользователь нажал "Да"
            onResult(true) // Вызываем коллбэк с true
        }
        .setNegativeButton("Нет") { dialog, which ->
            // Пользователь нажал "Нет"
            onResult(false) // Вызываем коллбэк с false
        }
        .setIcon(R.drawable.ic_dialog_alert)
        .show()
}