package com.example.merasaresto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.merasaresto.data.PreferenceManager
import com.example.merasaresto.screens.MainScreen
import com.example.merasaresto.ui.theme.MerasaRestoTheme

/**
 * Activity Utama - Single Activity Architecture
 * Aplikasi ini mengelola navigasi antar layar menggunakan Jetpack Compose Navigation
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Mengaktifkan tampilan full screen (tepi ke tepi)
        enableEdgeToEdge()
        
        setContent {
            val context = LocalContext.current
            
            // Inisialisasi PreferenceManager untuk mengelola data lokal
            val preferenceManager = remember { PreferenceManager(context) }
            
            // State untuk Dark Mode
            val isDarkTheme = remember { mutableStateOf(preferenceManager.isDarkMode()) }
            
            // Tema Aplikasi
            MerasaRestoTheme(darkTheme = isDarkTheme.value) {
                // Memanggil MainScreen yang berisi Bottom Navigation
                MainScreen(
                    preferenceManager = preferenceManager,
                    onThemeChange = { isDark -> isDarkTheme.value = isDark }
                )
            }
        }
    }
}
