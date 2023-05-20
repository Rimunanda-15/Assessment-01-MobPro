package com.d3if3105.mobpro.Assesment01.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d3if3105.mobpro.Assesment01.databinding.FragmentRumusBinding

class RumusFragment : Fragment() {
    private lateinit var binding: FragmentRumusBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRumusBinding.inflate(inflater, container, false)
        return binding.root
    }
}