package com.vietquoc.ceb029_nguyenquocviet.repository

import com.vietquoc.ceb029_nguyenquocviet.db.ComputerDb
import com.vietquoc.ceb029_nguyenquocviet.model.Computer

class ComputerRepository(private val db: ComputerDb) {

    suspend fun insert(computer: Computer) = db.getNoteDao().insert(computer)
    suspend fun update(computer: Computer) = db.getNoteDao().update(computer)
    suspend fun delete(computer: Computer) = db.getNoteDao().delete(computer)

    fun getAllNotes() = db.getNoteDao().getAllComputers()
    fun searchNote(query: String) = db.getNoteDao().searchComputer(query)

}