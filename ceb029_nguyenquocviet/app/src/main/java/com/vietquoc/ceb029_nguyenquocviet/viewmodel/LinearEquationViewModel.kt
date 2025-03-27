package com.vietquoc.ceb029_nguyenquocviet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LinearEquationViewModel @Inject constructor() : ViewModel() {

    private val _solution = MutableLiveData<String>()
    val solution: LiveData<String> get() = _solution

    fun solveEquation(a: Double, b: Double) {
        _solution.value = when {
            a == 0.0 && b == 0.0 -> "Phương trình có vô số nghiệm"
            a == 0.0 -> "Phương trình vô nghiệm"
            else -> "Nghiệm x = ${-b / a}"
        }
    }
}