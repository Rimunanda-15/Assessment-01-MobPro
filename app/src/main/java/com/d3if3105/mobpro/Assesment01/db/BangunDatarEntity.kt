package com.d3if3105.mobpro.Assesment01.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bangunDatar")
data class BangunDatarEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal:Long = System.currentTimeMillis(),
    var sisi1 :Double,
    var sisi2 :Double,
    var hasil :Double
)
