package com.d3if3105.mobpro.Assesment01.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BangunDatarEntity::class], version = 1, exportSchema = false)
abstract class BangunDatarDb : RoomDatabase() {
    abstract fun bangunDatarDao(): BangunDatarDao
    companion object {
        @Volatile
        private var INSTANCE: BangunDatarDb? = null
        fun getInstance(context: Context): BangunDatarDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BangunDatarDb::class.java,
                        "bangundatar.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}