package com.d3if3105.mobpro.Assesment01.ui

import androidx.lifecycle.ViewModel
import com.d3if3105.mobpro.Assesment01.db.BangunDatarDao

class HistoriViewModel(db: BangunDatarDao ) : ViewModel() {
    val data = db.getLastBangunDatar()
}