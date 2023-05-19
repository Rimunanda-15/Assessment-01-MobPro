package com.d3if3105.mobpro.Assesment01.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.d3if3105.mobpro.Assesment01.databinding.FragmentHitungBinding


class HitungFragment : Fragment() {
    private lateinit var binding: FragmentHitungBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHitungBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        binding.hitungButton.setOnClickListener {
            viewModel.hitungLuas(
                binding.ukuran1Inp.text.toString(),
                binding.ukuran2Inp.text.toString(),
                binding.jenisBangunDatarRadioGroup.checkedRadioButtonId
            )
        }

        viewModel.error.observe(viewLifecycleOwner, Observer { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        })

        viewModel.luas.observe(viewLifecycleOwner, Observer { luas ->
            binding.luasTextView.text = "%.0f".format(luas)
        })
    }
}