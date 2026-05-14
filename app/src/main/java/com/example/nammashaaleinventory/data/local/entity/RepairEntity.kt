package com.example.nammashaaleinventory.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repairs")
data class RepairEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val assetId: Long,
    val assetName: String,
    val condition: String,
    val requestDate: Long = System.currentTimeMillis(),
    val status: String = "Pending"
)