package com.d3if3105.mobpro.Assesment01.ui
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.d3if3105.mobpro.Assesment01.R
import com.d3if3105.mobpro.Assesment01.model.BangunDatar
import kotlin.math.PI
import kotlin.math.pow

class MainViewModel : ViewModel() {
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
    }
}
