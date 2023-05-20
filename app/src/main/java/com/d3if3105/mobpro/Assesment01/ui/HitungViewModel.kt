// HitungViewModel.kt
package com.d3if3105.mobpro.Assesment01.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.d3if3105.mobpro.Assesment01.DataStoreManager
import com.d3if3105.mobpro.Assesment01.R
import com.d3if3105.mobpro.Assesment01.db.BangunDatarDao
import com.d3if3105.mobpro.Assesment01.db.BangunDatarEntity
import com.d3if3105.mobpro.Assesment01.model.BangunDatar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.math.PI
import kotlin.math.pow

class HitungViewModel(private val bangunDatarDao: BangunDatarDao, application: Application) : AndroidViewModel(application) {
    val luas = MutableLiveData<Double>()
    val error = MutableLiveData<String>()

    fun hitungLuas(ukuran1Str: String, ukuran2Str: String, jenisBangunDatar: Int) {
        val ukuran1 = ukuran1Str.toDoubleOrNull()
        val ukuran2 = ukuran2Str.toDoubleOrNull()

        if (ukuran1 == null || ukuran2 == null) {
            error.value = "Harap masukkan ukuran terlebih dahulu"
            return
        }

        val bangunDatar = when (jenisBangunDatar) {
            R.id.persegi_radio_button -> BangunDatar.Persegi(ukuran1)
            R.id.persegi_panjang_radio_button -> BangunDatar.PersegiPanjang(ukuran1, ukuran2)
            R.id.lingkaran_radio_button -> BangunDatar.Lingkaran(ukuran1)
            R.id.segitiga_radio_button -> BangunDatar.Segitiga(ukuran1, ukuran2)
            else -> {
                error.value = "Harap pilih jenis bangun datar terlebih dahulu"
                return
            }
        }

        val luasValue = when (bangunDatar) {
            is BangunDatar.Persegi -> bangunDatar.sisi.pow(2)
            is BangunDatar.PersegiPanjang -> bangunDatar.panjang * bangunDatar.lebar
            is BangunDatar.Lingkaran -> PI * bangunDatar.jariJari.pow(2)
            is BangunDatar.Segitiga -> bangunDatar.alas * bangunDatar.tinggi / 2
        }

        luas.value = luasValue

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val bangunDatarEntity = BangunDatarEntity(
                    sisi1 = ukuran1,
                    sisi2 = ukuran2,
                    hasil = luasValue
                )
                bangunDatarDao.insert(bangunDatarEntity)

                val shape = when (jenisBangunDatar) {
                    R.id.persegi_radio_button -> "persegi"
                    R.id.persegi_panjang_radio_button -> "persegi_panjang"
                    R.id.lingkaran_radio_button -> "lingkaran"
                    R.id.segitiga_radio_button -> "segitiga"
                    else -> ""
                }
                if (shape.isNotEmpty()) {
                    DataStoreManager.saveLastUsedShape(getApplication(), shape)
                }
            }
        }
    }

    fun getLastUsedShape(requireContext: Context): String? {
        return runBlocking {
            val shape = withContext(Dispatchers.IO) {
                DataStoreManager.getLastUsedShape(requireContext)
            }
            shape
        }
    }
}
