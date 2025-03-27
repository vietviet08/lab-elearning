package com.vietquoc.ceb029_nguyenquocviet.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "computer")
@Parcelize
data class Computer(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val type: String,
    val quantity: Double,
    val price: Int
) : Parcelable
