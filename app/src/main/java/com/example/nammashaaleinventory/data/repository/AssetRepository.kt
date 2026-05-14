package com.example.nammashaaleinventory.data.repository

import androidx.lifecycle.LiveData
import com.example.nammashaaleinventory.data.local.dao.AssetDao
import com.example.nammashaaleinventory.data.local.entity.Asset

class AssetRepository(private val assetDao: AssetDao) {

    val allAssets: LiveData<List<Asset>> = assetDao.getAllAssets()
    val totalCount: LiveData<Int> = assetDao.getTotalCount()
    val needsRepairCount: LiveData<Int> = assetDao.getNeedsRepairCount()
    val workingCount: LiveData<Int> = assetDao.getWorkingCount()

    suspend fun insert(asset: Asset): Long {
        return assetDao.insert(asset)
    }

    suspend fun update(asset: Asset) {
        assetDao.update(asset)
    }

    suspend fun delete(asset: Asset) {
        assetDao.delete(asset)
    }

    suspend fun getAssetById(id: Long): Asset? {
        return assetDao.getAssetById(id)
    }

    fun searchAssets(query: String): LiveData<List<Asset>> {
        return assetDao.searchAssets(query)
    }

    fun getAssetsByCondition(condition: String): LiveData<List<Asset>> {
        return assetDao.getAssetsByCondition(condition)
    }

    suspend fun updateCondition(assetId: Long, newCondition: String, checkedDate: Long) {
        assetDao.updateCondition(assetId, newCondition, checkedDate)
    }
}