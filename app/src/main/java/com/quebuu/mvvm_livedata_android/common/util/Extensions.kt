package com.quebuu.mvvm_livedata_android.common.util

import android.app.Dialog
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.quebuu.mvvm_livedata_android.common.components.textinput.ComponentTextInput
import java.text.SimpleDateFormat
import java.util.*

fun ComponentTextInput.addTextChangedListener(event: (editable: Editable?) -> Unit) = run {
    this.getInputEditText().addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            event.invoke(p0)
        }
    })
}

fun ComponentTextInput.addTextChangedListenerClear() = run {
    this.getInputEditText().addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {}
    })
}

private fun localePeru() = Locale("es", "PE")

fun getFormatDay(): SimpleDateFormat {
    return SimpleDateFormat("HH:mm", localePeru())
}

fun getFormatDateTime(): SimpleDateFormat {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss", localePeru())
}

fun getFormatDateSlash(): SimpleDateFormat {
    return SimpleDateFormat("dd/MM/yyyy", localePeru())
}

fun getFormatMonthAndYear(): SimpleDateFormat {
    return SimpleDateFormat("MMMM, yyyy", localePeru())
}

fun getFormatDayOfWeek(): SimpleDateFormat {
    return SimpleDateFormat("EEEE", localePeru())
}

fun getFormatDayNumber(): SimpleDateFormat {
    return SimpleDateFormat("dd", localePeru())
}

fun Date.toCalendar(): Calendar {
    val cal = Calendar.getInstance()
    cal.time = this
    return cal
}

fun String.toCalendar(): Calendar {
    val cal = Calendar.getInstance()
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", localePeru())
    cal.time = sdf.parse(this)
    return cal
}

fun String.toCalendarAdd(type: Int, quantity: Int): Calendar {
    val cal = Calendar.getInstance()
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", localePeru())
    cal.time = sdf.parse(this)
    cal.add(type, quantity)
    return cal
}

fun Dialog.setTransparentBackground() {
    setOnShowListener {
        val bottomSheet = findViewById<View?>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.setBackgroundResource(android.R.color.transparent)
    }
}

fun randomAssistanceType(): String {
    when ((0..2).random()) {
        0 -> return "AS"
        1 -> return "FA"
        2 -> return ""
    }
    return ""
}

fun randomColor(): String {
    when ((0..3).random()) {
        0 -> return "#00B7BD"
        1 -> return "#4B70F1"
        2 -> return "#FE3053"
        3 -> return "#15BAEE"
    }
    return ""
}

fun isDateBeforeNow(dateString: String): Boolean {
    val date: Date = SimpleDateFormat("dd/MM/yyyy", localePeru()).parse(dateString)
    val dateNow = Date()
    return date.before(dateNow)
}

fun getDayNumberNow(): Int {
    val calendar: Calendar = Calendar.getInstance(localePeru())
    val dayNumber = calendar.get(Calendar.DAY_OF_WEEK)
    return if (dayNumber == 1) {
        7
    } else {
        dayNumber - 1
    }
}