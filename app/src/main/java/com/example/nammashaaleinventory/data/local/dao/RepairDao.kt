package com.example.nammashaaleinventory.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nammashaaleinventory.data.local.entity.RepairEntity

@Dao
interface RepairDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repair: RepairEntity): Long

    @Update
    suspend fun update(repair: RepairEntity)

    @Delete
    suspend fun delete(repair: RepairEntity)

    @Query("SELECT * FROM repairs ORDER BY requestDate DESC")
    fun getAllRepairs(): LiveData<List<RepairEntity>>

    @Query("SELECT * FROM repairs WHERE assetId = :assetId")
    fun getRepairsForAsset(assetId: Long): LiveData<List<RepairEntity>>
}