package com.d3if3105.mobpro.Assesment01.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.d3if3105.mobpro.Assesment01.databinding.FragmentHistoriBinding
import com.d3if3105.mobpro.Assesment01.db.BangunDatarDb
import com.d3if3105.mobpro.Assesment01.ui.hitung.HistoriViewModelFactory

class HistoriFragment : Fragment() {
    private lateinit var binding: FragmentHistoriBinding
    private lateinit var adapter: HistoriAdapter

    private val viewModel: HistoriViewModel by lazy {
        val db = BangunDatarDb.getInstance(requireContext())
        val factory = HistoriViewModelFactory(db.bangunDatarDao())
        ViewModelProvider(this, factory).get(HistoriViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi adapter
        adapter = HistoriAdapter(requireContext())

        // Mengatur RecyclerView dan adapter
        binding.recyclerViewHistori.adapter = adapter
        binding.recyclerViewHistori.layoutManager = LinearLayoutManager(requireContext())

        // Mengamati data dari ViewModel
        viewModel.data.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
        }
    }
}

