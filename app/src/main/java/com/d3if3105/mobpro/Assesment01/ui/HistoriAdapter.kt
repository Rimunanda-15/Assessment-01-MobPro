package com.d3if3105.mobpro.Assesment01.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.d3if3105.mobpro.Assesment01.R
import com.d3if3105.mobpro.Assesment01.db.BangunDatarEntity
import com.google.android.material.textview.MaterialTextView

class HistoriAdapter(private val context: Context) : RecyclerView.Adapter<HistoriAdapter.ViewHolder>() {

    private var data: List<BangunDatarEntity> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_histori, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: BangunDatarEntity?) {
        data = if (newData != null) listOf(newData) else emptyList()
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTanggal: MaterialTextView = itemView.findViewById(R.id.textViewTanggal)
        private val tvSisi1: MaterialTextView = itemView.findViewById(R.id.textViewUkuran)
        private val tvSisi2: MaterialTextView = itemView.findViewById(R.id.textViewUkuran2)
        private val tvHasil: MaterialTextView = itemView.findViewById(R.id.textViewLuas)

        fun bind(item: BangunDatarEntity) {
            tvTanggal.text = item.tanggal.toString()
            tvSisi1.text = context.getString(R.string.sisi_1_label, item.sisi1.toString())
            tvSisi2.text = context.getString(R.string.sisi_2_label, item.sisi2.toString())
            tvHasil.text = context.getString(R.string.hasil_label, item.hasil.toString())
        }
    }
}
