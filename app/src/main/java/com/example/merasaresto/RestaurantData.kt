package com.example.merasaresto

import android.content.Context
import android.content.SharedPreferences
import com.example.merasaresto.model.RestaurantProfile

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("RestaurantPrefs", Context.MODE_PRIVATE)

    fun saveProfile(profile: RestaurantProfile) {
        sharedPreferences.edit().apply {
            putString("name", profile.name)
            putString("address", profile.address)
            putString("description", profile.description)
            putString("openingHours", profile.openingHours)
            apply()
        }
    }

    fun getProfile(): RestaurantProfile {
        return RestaurantProfile(
            name = sharedPreferences.getString("name", "Merasa Resto") ?: "Merasa Resto",
            address = sharedPreferences.getString("address", "Jl. Rasa No. 123") ?: "Jl. Rasa No. 123",
            description = sharedPreferences.getString("description", "Restoran dengan cita rasa nusantara.") ?: "Restoran dengan cita rasa nusantara.",
            openingHours = sharedPreferences.getString("openingHours", "09:00 - 21:00") ?: "09:00 - 21:00"
        )
    }
}
