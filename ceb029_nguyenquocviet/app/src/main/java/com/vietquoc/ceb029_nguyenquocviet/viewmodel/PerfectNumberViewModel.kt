package com.vietquoc.ceb029_nguyenquocviet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PerfectNumberViewModel @Inject constructor() : ViewModel() {

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> get() = _result

    fun checkPerfectNumber(number: Int) {
        if (isPerfectNumber(number)) {
            _result.value = "$number là số hoàn hảo"
        } else {
            _result.value = "$number không phải số hoàn hảo"
        }
    }

    private fun isPerfectNumber(n: Int): Boolean {
        if (n < 2) return false
        var sum = 1
        for (i in 2..n / 2) {
            if (n % i == 0) {
                sum += i
            }
        }
        return sum == n
    }
}