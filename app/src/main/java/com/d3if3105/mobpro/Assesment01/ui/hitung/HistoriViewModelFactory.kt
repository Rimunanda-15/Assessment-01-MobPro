package com.d3if3105.mobpro.Assesment01.ui.hitung

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.d3if3105.mobpro.Assesment01.db.BangunDatarDao
import com.d3if3105.mobpro.Assesment01.ui.HistoriViewModel

class HistoriViewModelFactory(
    private val db: BangunDatarDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoriViewModel::class.java)) {
            return HistoriViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
