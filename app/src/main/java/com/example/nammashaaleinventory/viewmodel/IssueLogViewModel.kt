package com.example.nammashaaleinventory.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.nammashaaleinventory.data.local.AppDatabase
import com.example.nammashaaleinventory.data.local.entity.IssueLogEntity
import com.example.nammashaaleinventory.data.repository.IssueLogRepository
import kotlinx.coroutines.launch

class IssueLogViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: IssueLogRepository
    val allIssueLogs: LiveData<List<IssueLogEntity>>

    init {
        val issueLogDao = AppDatabase.getDatabase(application).issueLogDao()
        repository = IssueLogRepository(issueLogDao)
        allIssueLogs = repository.allIssueLogs
    }

    fun insert(issueLog: IssueLogEntity) = viewModelScope.launch {
        repository.insert(issueLog)
    }

    fun delete(issueLog: IssueLogEntity) = viewModelScope.launch {
        repository.delete(issueLog)
    }
}