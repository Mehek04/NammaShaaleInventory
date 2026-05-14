package com.example.nammashaaleinventory.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nammashaaleinventory.data.local.entity.Asset
/**
 * Data Access Object for Asset operations
 */
@Dao
interface AssetDao {

    // Insert new asset
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asset: Asset): Long

    // Update existing asset
    @Update
    suspend fun update(asset: Asset)

    // Delete asset
    @Delete
    suspend fun delete(asset: Asset)

    // Get all assets as LiveData
    @Query("SELECT * FROM assets ORDER BY addedDate DESC")
    fun getAllAssets(): LiveData<List<Asset>>

    // Get asset by ID
    @Query("SELECT * FROM assets WHERE id = :id")
    suspend fun getAssetById(id: Long): Asset?

    // Search assets by name
    @Query("SELECT * FROM assets WHERE name LIKE '%' || :query || '%'")
    fun searchAssets(query: String): LiveData<List<Asset>>

    // Get total count of assets
    @Query("SELECT COUNT(*) FROM assets")
    fun getTotalCount(): LiveData<Int>

    // Get count of assets needing repair
    @Query("SELECT COUNT(*) FROM assets WHERE condition = 'Needs Repair' OR condition = 'Broken'")
    fun getNeedsRepairCount(): LiveData<Int>

    // Get count of working assets
    @Query("SELECT COUNT(*) FROM assets WHERE condition = 'Working'")
    fun getWorkingCount(): LiveData<Int>

    // Get assets by condition
    @Query("SELECT * FROM assets WHERE condition = :condition")
    fun getAssetsByCondition(condition: String): LiveData<List<Asset>>

    // Update condition of asset (for monthly health check)
    @Query("UPDATE assets SET condition = :newCondition, lastCheckedDate = :checkedDate WHERE id = :assetId")
    suspend fun updateCondition(assetId: Long, newCondition: String, checkedDate: Long)
}