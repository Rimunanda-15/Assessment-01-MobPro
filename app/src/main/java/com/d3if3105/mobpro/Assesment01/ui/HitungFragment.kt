package com.d3if3105.mobpro.Assesment01.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.d3if3105.mobpro.Assesment01.R
import com.d3if3105.mobpro.Assesment01.databinding.FragmentHitungBinding
import com.d3if3105.mobpro.Assesment01.db.BangunDatarDb
import com.d3if3105.mobpro.Assesment01.ui.hitung.HitungViewModelFactory

class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding
    private lateinit var viewModel: HitungViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitungBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bangunDatarDao = BangunDatarDb.getInstance(requireContext()).bangunDatarDao()
        val viewModelFactory = HitungViewModelFactory(bangunDatarDao)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HitungViewModel::class.java)

        binding.hitungButton.setOnClickListener {
            viewModel.hitungLuas(
                binding.ukuran1Inp.text.toString(),
                binding.ukuran2Inp.text.toString(),
                binding.jenisBangunDatarRadioGroup.checkedRadioButtonId
            )
        }

        binding.rumus.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_hitungFragment_to_rumusFragment
            )
        }

        viewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        })

        viewModel.luas.observe(viewLifecycleOwner, Observer { luas ->
            binding.luasTextView.text = "%.0f".format(luas)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                findNavController().navigate(R.id.action_hitungFragment_to_aboutFragment)
                return true
            }
            R.id.menu_lihat_rumu_diwebsite -> {
                val url = "https://www.cnnindonesia.com/edukasi/20221129094754-569-880180/rumus-bangun-datar-lengkap-untuk-menghitung-luas-dan-keliling"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
