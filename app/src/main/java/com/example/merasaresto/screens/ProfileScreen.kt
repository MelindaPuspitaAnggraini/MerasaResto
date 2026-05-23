package com.example.merasaresto.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import com.example.merasaresto.R
import com.example.merasaresto.data.PreferenceManager
import com.example.merasaresto.model.RestaurantProfile
import com.example.merasaresto.navigation.Screen
import com.example.merasaresto.ui.theme.MerasaRestoTheme

/**
 * Tampilan Profil Restoran - Desain Elegan & Modern dengan Toggle Tema
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController, 
    preferenceManager: PreferenceManager,
    onThemeChange: (Boolean) -> Unit
) {
    // Re-fetch profile whenever the screen is composed or when coming back from navigation
    val profile = remember(navController.currentBackStackEntry) { 
        preferenceManager.getProfile() 
    }
    
    // State untuk Switch Tema
    val isDarkMode = remember { mutableStateOf(preferenceManager.isDarkMode()) }

    ProfileScreenContent(
        profile = profile,
        isDarkMode = isDarkMode.value,
        onThemeToggle = { checked ->
            isDarkMode.value = checked
            preferenceManager.setDarkMode(checked)
            onThemeChange(checked)
        },
        onBackClick = { navController.popBackStack() },
        onEditClick = { navController.navigate(Screen.EditProfile.route) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenContent(
    profile: RestaurantProfile,
    isDarkMode: Boolean,
    onThemeToggle: (Boolean) -> Unit,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Profil Restoran", 
                        fontWeight = FontWeight.ExtraBold,
                        color = if (isDarkMode) Color.White else Color(0xFF5D1D19)
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali",
                            tint = if (isDarkMode) Color.White else Color(0xFF5D1D19)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onThemeToggle(!isDarkMode) }) {
                        Icon(
                            imageVector = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Ubah Tema",
                            tint = if (isDarkMode) Color.White else Color(0xFF5D1D19)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (isDarkMode) Color(0xFF3E1411) else Color(0xFF5D1D19).copy(alpha = 0.15f)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onEditClick,
                containerColor = if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19),
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit Profil")
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            if (isDarkMode) Color(0xFF3E1411) else Color(0xFF5D1D19).copy(alpha = 0.15f),
                            MaterialTheme.colorScheme.background
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo Transparan (Tanpa Lapisan Belakang)
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Restoran",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Informasi Profil dalam Card Modern
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        ProfileInfoItem(label = "Nama Restoran", value = profile.name, isDarkMode = isDarkMode)
                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.Gray.copy(alpha = 0.2f))
                        ProfileInfoItem(label = "Alamat", value = profile.address, isDarkMode = isDarkMode)
                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.Gray.copy(alpha = 0.2f))
                        ProfileInfoItem(label = "Deskripsi", value = profile.description, isDarkMode = isDarkMode)
                        HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp), color = Color.Gray.copy(alpha = 0.2f))
                        ProfileInfoItem(label = "Jam Buka", value = profile.openingHours, isDarkMode = isDarkMode)
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileInfoItem(label: String, value: String, isDarkMode: Boolean) {
    Column {
        Text(
            text = label, 
            fontSize = 12.sp, 
            color = if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19),
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value, 
            fontSize = 17.sp, 
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    MerasaRestoTheme {
        ProfileScreenContent(
            profile = RestaurantProfile(
                name = "Merasa Resto",
                address = "Jl. Kertanegara No.176, Kota Malang",
                description = "Restoran yang menghadirkan pengalaman kuliner dengan cita rasa nusantara dan menggugah selera.",
                openingHours = "09:00 - 22:00"
            ),
            isDarkMode = false,
            onThemeToggle = {},
            onBackClick = {},
            onEditClick = {}
        )
    }
}
