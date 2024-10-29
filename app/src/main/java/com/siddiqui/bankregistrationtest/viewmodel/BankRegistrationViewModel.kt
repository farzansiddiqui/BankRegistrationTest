package com.siddiqui.bankregistrationtest.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.siddiqui.bankregistrationtest.utils.isValidDate
import com.siddiqui.bankregistrationtest.utils.isValidPAN

class BankRegistrationViewModel : ViewModel() {

    val panNumber = MutableLiveData<String>()
    val day = MutableLiveData<String>()
    val month = MutableLiveData<String>()
    val year = MutableLiveData<String>()

    // LiveData that checks if both date and PAN validations are true
    @RequiresApi(Build.VERSION_CODES.O)
    val isFormValid: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(day) { value = checkFormValidity() }
        addSource(month) { value = checkFormValidity() }
        addSource(year) { value = checkFormValidity() }
        addSource(panNumber) { value = checkFormValidity() }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkFormValidity(): Boolean {
        val isValidDate = isValidDate(day.value, month.value, year.value)
        val isPanValid = panNumber.value?.isValidPAN() == true
        return isValidDate && isPanValid
    }



}