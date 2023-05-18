package com.d3if3105.mobpro.Assesment01.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.d3if3105.mobpro.Assesment01.databinding.ActivityMainBinding
import com.d3if3105.mobpro.Assesment01.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.hitungButton.setOnClickListener {
            viewModel.hitungLuas(
                binding.ukuran1Inp.text.toString(),
                binding.ukuran2Inp.text.toString(),
                binding.jenisBangunDatarRadioGroup.checkedRadioButtonId
            )
        }

        viewModel.error.observe(this, Observer { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        })

        viewModel.luas.observe(this, Observer { luas ->
            binding.luasTextView.text = "%.0f".format(luas)
        })
    }
}