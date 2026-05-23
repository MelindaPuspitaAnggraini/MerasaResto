package com.example.merasaresto.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Definisi rute navigasi untuk seluruh aplikasi
 */
sealed class Screen(val route: String, val title: String = "", val icon: ImageVector? = null) {
    object Home : Screen("home", "Beranda", Icons.Default.Home)
    object Menu : Screen("menu", "Menu", Icons.Default.RestaurantMenu)
    object Profile : Screen("profile", "Profil", Icons.Default.Person)
    object EditProfile : Screen("edit_profile")
    
    // Rute dengan argumen
    object DetailMenu : Screen("detail/{menuId}") {
        fun createRoute(menuId: String) = "detail/$menuId"
    }
}
