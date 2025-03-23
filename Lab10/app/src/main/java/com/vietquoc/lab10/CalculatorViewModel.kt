package com.vietquoc.lab10

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {
    private val _result = MutableLiveData<Int>()
    val result: LiveData<Int> = _result

    fun calculateSum(a: Int, b: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            val sum = a + b
            _result.postValue(sum)
        }
    }

    fun calculateDifference(a: Int, b: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            val difference = a - b
            _result.postValue(difference)
        }
    }
}
