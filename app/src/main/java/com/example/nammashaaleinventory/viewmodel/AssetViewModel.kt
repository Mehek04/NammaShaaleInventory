package com.example.nammashaaleinventory.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.nammashaaleinventory.data.local.AppDatabase
import com.example.nammashaaleinventory.data.local.entity.Asset
import com.example.nammashaaleinventory.data.repository.AssetRepository
import kotlinx.coroutines.launch

class AssetViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AssetRepository
    val allAssets: LiveData<List<Asset>>
    val totalCount: LiveData<Int>
    val needsRepairCount: LiveData<Int>
    val workingCount: LiveData<Int>

    private val _searchQuery = MutableLiveData<String>("")
    val searchResults: LiveData<List<Asset>>

    init {
        val assetDao = AppDatabase.getDatabase(application).assetDao()
        repository = AssetRepository(assetDao)
        allAssets = repository.allAssets
        totalCount = repository.totalCount
        needsRepairCount = repository.needsRepairCount
        workingCount = repository.workingCount
        searchResults = _searchQuery.switchMap { query ->
            repository.searchAssets(query)
        }
    }

    fun insert(asset: Asset) = viewModelScope.launch {
        repository.insert(asset)
    }

    fun update(asset: Asset) = viewModelScope.launch {
        repository.update(asset)
    }

    fun delete(asset: Asset) = viewModelScope.launch {
        repository.delete(asset)
    }

    fun searchAssets(query: String) {
        _searchQuery.value = query
    }

    fun updateCondition(assetId: Long, newCondition: String) = viewModelScope.launch {
        repository.updateCondition(assetId, newCondition, System.currentTimeMillis())
    }

    suspend fun getAssetById(id: Long): Asset? {
        return repository.getAssetById(id)
    }

    fun getAssetsByCondition(condition: String): LiveData<List<Asset>> {
        return repository.getAssetsByCondition(condition)
    }
}