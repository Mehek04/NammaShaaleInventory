package com.example.nammashaaleinventory.data.repository

import androidx.lifecycle.LiveData
import com.example.nammashaaleinventory.data.local.dao.IssueLogDao
import com.example.nammashaaleinventory.data.local.entity.IssueLogEntity

class IssueLogRepository(private val issueLogDao: IssueLogDao) {

    val allIssueLogs: LiveData<List<IssueLogEntity>> = issueLogDao.getAllIssueLogs()
    val issueCount: LiveData<Int> = issueLogDao.getIssueCount()

    suspend fun insert(issueLog: IssueLogEntity): Long {
        return issueLogDao.insert(issueLog)
    }

    suspend fun delete(issueLog: IssueLogEntity) {
        issueLogDao.delete(issueLog)
    }
}