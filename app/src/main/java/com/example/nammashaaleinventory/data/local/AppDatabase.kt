package com.example.nammashaaleinventory.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nammashaaleinventory.data.local.dao.AssetDao
import com.example.nammashaaleinventory.data.local.dao.IssueLogDao
import com.example.nammashaaleinventory.data.local.dao.RepairDao
import com.example.nammashaaleinventory.data.local.entity.Asset
import com.example.nammashaaleinventory.data.local.entity.IssueLogEntity
import com.example.nammashaaleinventory.data.local.entity.RepairEntity

@Database(
    entities = [Asset::class, IssueLogEntity::class, RepairEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun assetDao(): AssetDao
    abstract fun issueLogDao(): IssueLogDao
    abstract fun repairDao(): RepairDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "namma_shaale_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}