package com.example.nammashaaleinventory.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "assets")
data class Asset(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val serialNumber: String,
    val category: String,
    val condition: String,
    val photoPath: String = "",
    val addedDate: Long = System.currentTimeMillis(),
    val lastCheckedDate: Long = System.currentTimeMillis()
)