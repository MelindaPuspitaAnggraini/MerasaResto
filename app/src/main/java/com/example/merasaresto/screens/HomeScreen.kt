package com.example.merasaresto.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.merasaresto.data.PreferenceManager
import com.example.merasaresto.R
import androidx.compose.ui.tooling.preview.Preview
import com.example.merasaresto.ui.theme.MerasaRestoTheme

/**
 * Tampilan Utama (Home) - Menampilkan Logo, Nama, dan Deskripsi Lengkap Restoran
 */
@Composable
fun HomeScreen(navController: NavController, preferenceManager: PreferenceManager) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val profile = remember(navBackStackEntry) { preferenceManager.getProfile() }
    
    // Sinkronkan status Dark Mode dari PreferenceManager
    val isDarkMode = preferenceManager.isDarkMode()
    
    HomeContent(
        profileName = profile.name,
        isDarkMode = isDarkMode
    )
}

@Composable
fun HomeContent(
    profileName: String,
    isDarkMode: Boolean,
    modifier: Modifier = Modifier,
    initialVisibility: Boolean = false
) {
    var visible by remember { mutableStateOf(initialVisibility) }
    val scrollState = rememberScrollState()
    
    LaunchedEffect(Unit) { visible = true }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        if (isDarkMode) Color(0xFF3E1411) else Color(0xFF5D1D19).copy(alpha = 0.25f),
                        MaterialTheme.colorScheme.background
                    )
                )
            )
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically { it / 2 },
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Logo Restoran
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(240.dp)
                        .padding(bottom = 24.dp)
                )

                // Teks Selamat Datang
                Text(
                    text = "Selamat Datang",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (isDarkMode) Color.White else Color(0xFF5D1D19),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Deskripsi Singkat
                Text(
                    text = "Merasa Resto menyajikan hidangan khas Nusantara dengan cita rasa autentik dan suasana yang nyaman.",
                    fontSize = 16.sp,
                    color = if (isDarkMode) Color.White.copy(alpha = 0.7f) else Color.Black.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MerasaRestoTheme(dynamicColor = false) {
        HomeContent(
            profileName = "Merasa Resto",
            isDarkMode = false,
            initialVisibility = true
        )
    }
}
