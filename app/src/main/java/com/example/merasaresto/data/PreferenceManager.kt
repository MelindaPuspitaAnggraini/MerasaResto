package com.example.merasaresto.data

import android.content.Context
import android.content.SharedPreferences
import com.example.merasaresto.model.RestaurantProfile

/**
 * Manager untuk mengelola SharedPreferences (penyimpanan data sederhana)
 */
class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("RestaurantPrefs", Context.MODE_PRIVATE)

    // Menyimpan data profil restoran
    fun saveProfile(profile: RestaurantProfile) {
        sharedPreferences.edit().apply {
            putString("name", profile.name)
            putString("address", profile.address)
            putString("description", profile.description)
            putString("openingHours", profile.openingHours)
            apply()
        }
    }

    // Mengambil data profil restoran
    fun getProfile(): RestaurantProfile {
        return RestaurantProfile(
            name = sharedPreferences.getString("name", "Selamat Datang") ?: "Selamat Datang",
            address = sharedPreferences.getString("address", "Jl. Rasa No. 123, Jakarta") ?: "Jl. Rasa No. 123, Jakarta",
            description = sharedPreferences.getString("description", "Restoran dengan cita rasa nusantara yang autentik dan modern.") ?: "Restoran dengan cita rasa nusantara yang autentik dan modern.",
            openingHours = sharedPreferences.getString("openingHours", "09:00 - 22:00") ?: "09:00 - 22:00"
        )
    }

    // Fitur Dark Mode Toggle
    fun isDarkMode(): Boolean {
        return sharedPreferences.getBoolean("dark_mode", false)
    }

    fun setDarkMode(enabled: Boolean) {
        sharedPreferences.edit().putBoolean("dark_mode", enabled).apply()
    }
}
