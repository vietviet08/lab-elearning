package com.vietquoc.ceb029_nguyenquocviet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vietquoc.ceb029_nguyenquocviet.model.Computer
import com.vietquoc.ceb029_nguyenquocviet.repository.ComputerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComputerViewModel @Inject constructor(
    private val computerRepository: ComputerRepository
) : ViewModel() {

    fun addComputer(computer: Computer) = viewModelScope.launch {
        computerRepository.insert(computer = computer)
    }

    fun updateComputer(computer: Computer) = viewModelScope.launch {
        computerRepository.update(computer = computer)
    }

    fun deleteComputer(computer: Computer) = viewModelScope.launch {
        computerRepository.delete(computer)
    }

    fun getAllComputer() = computerRepository.getAllNotes()

    fun searchComputer(query: String) = computerRepository.searchNote(query)

}