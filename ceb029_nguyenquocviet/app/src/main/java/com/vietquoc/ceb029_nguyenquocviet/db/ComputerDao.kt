package com.vietquoc.ceb029_nguyenquocviet.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.vietquoc.ceb029_nguyenquocviet.model.Computer

@Dao
interface ComputerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(computer: Computer)

    @Update
    suspend fun update(computer: Computer)

    @Delete
    suspend fun delete(computer: Computer)

    @Query("SELECT * FROM computer ORDER BY id DESC")
    fun getAllComputers(): LiveData<List<Computer>>

    @Query("SELECT * FROM computer WHERE id =:id")
    fun getComputerById(id: Int): LiveData<Computer>

    @Query("SELECT * FROM computer WHERE name LIKE :query OR type LIKE :query ORDER BY id DESC")
    fun searchComputer(query: String): LiveData<List<Computer>>
}