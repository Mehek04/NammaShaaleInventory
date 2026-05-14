package com.example.nammashaaleinventory.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.nammashaaleinventory.data.local.AppDatabase
import com.example.nammashaaleinventory.data.local.entity.RepairEntity
import com.example.nammashaaleinventory.data.repository.RepairRepository
import kotlinx.coroutines.launch

class RepairViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RepairRepository
    val allRepairs: LiveData<List<RepairEntity>>

    init {
        val repairDao = AppDatabase.getDatabase(application).repairDao()
        repository = RepairRepository(repairDao)
        allRepairs = repository.allRepairs
    }

    fun insert(repair: RepairEntity) = viewModelScope.launch {
        repository.insert(repair)
    }

    fun update(repair: RepairEntity) = viewModelScope.launch {
        repository.update(repair)
    }

    fun delete(repair: RepairEntity) = viewModelScope.launch {
        repository.delete(repair)
    }
}