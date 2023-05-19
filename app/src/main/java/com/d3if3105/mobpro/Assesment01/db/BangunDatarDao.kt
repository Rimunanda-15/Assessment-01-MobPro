package com.d3if3105.mobpro.Assesment01.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query


@androidx.room.Dao
interface BangunDatarDao {
    @Insert
    fun insert(bangunDatar: BangunDatarEntity)

    @Query("SELECT * FROM bangunDatar ORDER BY id DESC ")
    fun getLastBangunDatar(): LiveData<BangunDatarEntity?>
}