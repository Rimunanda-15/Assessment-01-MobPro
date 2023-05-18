package com.d3if3105.mobpro.Assesment01.model

sealed class BangunDatar {
    data class Persegi(val sisi: Double) : BangunDatar()
    data class PersegiPanjang(val panjang: Double, val lebar: Double) : BangunDatar()
    data class Lingkaran(val jariJari: Double) : BangunDatar()
    data class Segitiga(val alas: Double, val tinggi: Double) : BangunDatar()
}
