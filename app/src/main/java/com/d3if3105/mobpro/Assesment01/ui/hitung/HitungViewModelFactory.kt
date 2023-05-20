package com.d3if3105.mobpro.Assesment01.ui.hitung

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if3105.mobpro.Assesment01.db.BangunDatarDao
import com.d3if3105.mobpro.Assesment01.ui.HitungViewModel

class HitungViewModelFactory(
    private val bangunDatarDao: BangunDatarDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HitungViewModel::class.java)) {
            return HitungViewModel(bangunDatarDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
