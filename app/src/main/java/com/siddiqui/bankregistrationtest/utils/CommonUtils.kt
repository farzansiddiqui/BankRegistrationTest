package com.siddiqui.bankregistrationtest.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DateTimeException
import java.time.LocalDate



fun String.isValidPAN(): Boolean {
    return matches(Regex("[A-Z]{5}[0-9]{4}[A-Z]"))
}

@RequiresApi(Build.VERSION_CODES.O)
fun isValidDate(day: String?, month: String?, year: String?): Boolean {
    if (day.isNullOrEmpty() || month.isNullOrEmpty() || year.isNullOrEmpty()) return false

    val dayInt = day.toIntOrNull() ?: return false
    val monthInt = month.toIntOrNull() ?: return false
    val yearInt = year.toIntOrNull() ?: return false

    // Basic range checks
    if (dayInt !in 1..31 || monthInt !in 1..12 || yearInt !in 1900..2100) return false

    return try {
        // Validate using LocalDate
        LocalDate.of(yearInt, monthInt, dayInt)
        true
    } catch (e: DateTimeException) {
        // Date is invalid if exception is thrown
        false
    }
}
