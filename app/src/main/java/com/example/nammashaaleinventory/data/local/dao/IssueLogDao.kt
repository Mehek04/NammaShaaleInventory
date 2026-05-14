package com.example.nammashaaleinventory.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nammashaaleinventory.data.local.entity.IssueLogEntity

@Dao
interface IssueLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(issueLog: IssueLogEntity): Long

    @Update
    suspend fun update(issueLog: IssueLogEntity)

    @Delete
    suspend fun delete(issueLog: IssueLogEntity)

    @Query("SELECT * FROM issue_logs ORDER BY date DESC")
    fun getAllIssueLogs(): LiveData<List<IssueLogEntity>>

    @Query("SELECT COUNT(*) FROM issue_logs")
    fun getIssueCount(): LiveData<Int>
}