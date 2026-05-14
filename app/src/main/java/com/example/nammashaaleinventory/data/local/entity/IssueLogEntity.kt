package com.example.nammashaaleinventory.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issue_logs")
data class IssueLogEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val description: String,
    val date: Long,
    val assetId: Long = 0
)