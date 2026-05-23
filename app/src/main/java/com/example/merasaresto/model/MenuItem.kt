package com.example.merasaresto.model

import com.example.merasaresto.R

/**
 * Model data untuk item menu restoran
 */
data class MenuItem(
    val id: String,
    val name: String,
    val price: String,
    val description: String,
    val imageRes: Int // Menggunakan resource ID (R.drawable.nama_file)
)

/**
 * Data menu dummy - Pastikan file gambar ada di folder res/drawable
 * Jika belum ada gambar, sementara bisa menggunakan android.R.drawable.ic_menu_gallery
 */
val dummyMenu: List<MenuItem>
    get() = listOf(
        MenuItem("1", "Nasi Goreng Spesial", "Rp 25.000", "Nasi goreng dengan telur mata sapi, ayam suwir, dan kerupuk udang.", R.drawable.nasgor),
        MenuItem("2", "Bakmi Goreng Spesial", "Rp 25.000", "Mie kenyal dengan topping ayam dan sawi segar, menciptakan rasa gurih yang lezat.", R.drawable.bakmi),
        MenuItem("3", "Capcay Spesial", "Rp 30.000", "Perpaduan aneka sayuran segar dengan potongan ayam dan bakso yang dimasak dalam bumbu gurih khas MeRasa.", R.drawable.capcay),
        MenuItem("4", "Es Teh Manis", "Rp 5.000", "Teh melati pilihan yang disajikan dingin dengan gula asli.", R.drawable.teh),
        MenuItem("5", "Es Jeruk Peras", "Rp 8.000", "Jeruk peras murni yang memberikan kesegaran alami.", R.drawable.jeruk)
    )

data class RestaurantProfile(
    val name: String,
    val address: String,
    val description: String,
    val openingHours: String
)
