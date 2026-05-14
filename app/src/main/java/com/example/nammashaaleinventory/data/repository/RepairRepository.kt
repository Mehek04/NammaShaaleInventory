package com.example.nammashaaleinventory.data.repository

import androidx.lifecycle.LiveData
import com.example.nammashaaleinventory.data.local.dao.RepairDao
import com.example.nammashaaleinventory.data.local.entity.RepairEntity

class RepairRepository(private val repairDao: RepairDao) {

    val allRepairs: LiveData<List<RepairEntity>> = repairDao.getAllRepairs()

    suspend fun insert(repair: RepairEntity): Long {
        return repairDao.insert(repair)
    }

    suspend fun update(repair: RepairEntity) {
        repairDao.update(repair)
    }

    suspend fun delete(repair: RepairEntity) {
        repairDao.delete(repair)
    }
}