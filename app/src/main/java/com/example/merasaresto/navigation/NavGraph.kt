package com.example.merasaresto.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.merasaresto.data.PreferenceManager
import com.example.merasaresto.screens.*

/**
 * NavGraph untuk mengatur alur navigasi antar layar dengan Animasi Modern
 */
@Composable
fun NavGraph(
    navController: NavHostController, 
    preferenceManager: PreferenceManager,
    onThemeChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier,
        enterTransition = {
            fadeIn(animationSpec = tween(500)) + slideInHorizontally(initialOffsetX = { 1000 })
        },
        exitTransition = {
            fadeOut(animationSpec = tween(500)) + slideOutHorizontally(targetOffsetX = { -1000 })
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(500)) + slideInHorizontally(initialOffsetX = { -1000 })
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(500)) + slideOutHorizontally(targetOffsetX = { 1000 })
        }
    ) {
        // Layar Beranda
        composable(Screen.Home.route) {
            HomeScreen(navController, preferenceManager)
        }

        // Layar Daftar Menu
        composable(Screen.Menu.route) {
            MenuScreen(navController, preferenceManager)
        }

        // Layar Detail Menu dengan Argument ID
        composable(Screen.DetailMenu.route) { backStackEntry ->
            val menuId = backStackEntry.arguments?.getString("menuId")
            DetailMenuScreen(menuId, navController, preferenceManager)
        }

        // Layar Profil
        composable(Screen.Profile.route) {
            ProfileScreen(navController, preferenceManager, onThemeChange)
        }

        // Layar Edit Profil
        composable(Screen.EditProfile.route) {
            EditProfileScreen(navController, preferenceManager)
        }
    }
}
