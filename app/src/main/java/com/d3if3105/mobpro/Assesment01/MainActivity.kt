package com.d3if3105.mobpro.Assesment01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.d3if3105.mobpro.Assesment01.databinding.ActivityMainBinding
import kotlin.math.PI
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hitungButton.setOnClickListener {
            hitungLuas()
        }

    }

    private fun hitungLuas() {
        val ukuran1 = binding.ukuran1Inp.text.toString().toDoubleOrNull()
        val ukuran2 = binding.ukuran2Inp.text.toString().toDoubleOrNull()

        if (ukuran1 == null || ukuran2 == null) {
            Toast.makeText(this, "Harap masukkan ukuran terlebih dahulu", Toast.LENGTH_SHORT).show()
            return
        }

        val jenisBangunDatar = binding.jenisBangunDatarRadioGroup.checkedRadioButtonId
        val luas = when (jenisBangunDatar) {
            R.id.persegi_radio_button -> ukuran1.pow(2)
            R.id.persegi_panjang_radio_button -> ukuran1 * ukuran2
            R.id.lingkaran_radio_button -> PI * ukuran1.pow(2)
            R.id.segitiga_radio_button -> ukuran1 * ukuran2 / 2
            else ->  {
                Toast.makeText(this, "Harap pilih jenis bangun datar terlebih dahulu", Toast.LENGTH_SHORT).show()
                null
            }
        }

        binding.luasTextView.text = "%.0f".format(luas)
    }

}