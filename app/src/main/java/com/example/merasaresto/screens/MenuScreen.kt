package com.example.merasaresto.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.merasaresto.data.PreferenceManager
import com.example.merasaresto.model.MenuItem
import com.example.merasaresto.model.dummyMenu
import com.example.merasaresto.navigation.Screen
import com.example.merasaresto.ui.theme.BrickRed
import com.example.merasaresto.ui.theme.MerasaRestoTheme

/**
 * Tampilan Daftar Menu yang Modern dan Elegan dengan tema Brick Red
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController, preferenceManager: PreferenceManager) {
    val isDarkMode = preferenceManager.isDarkMode()
    MenuContent(
        isDarkMode = isDarkMode,
        onBackClick = { navController.popBackStack() },
        onItemClick = { item ->
            // Navigasi ke DetailMenuScreen dengan argumen ID/Nama
            navController.navigate(Screen.DetailMenu.createRoute(item.id))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuContent(isDarkMode: Boolean, onBackClick: () -> Unit, onItemClick: (MenuItem) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Menu Spesial",
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
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        // Latar Belakang Gradasi Merah Bata Elegan
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
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // dummyMenu berisi 5 item (makanan & minuman) dari data/model
                items(dummyMenu) { item ->
                    MenuCard(item = item, isDarkMode = isDarkMode) {
                        onItemClick(item)
                    }
                }
            }
        }
    }
}

/**
 * Komponen Card Menu dengan desain modern dan tema Brick Red
 */
@Composable
fun MenuCard(item: MenuItem, isDarkMode: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Container Gambar Menu dengan aksen Brick Red
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(if (isDarkMode) Color(0xFF8B433E).copy(alpha = 0.15f) else Color(0xFF5D1D19).copy(alpha = 0.08f))
            ) {
                Image(
                    painter = painterResource(id = item.imageRes),
                    contentDescription = item.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = item.price,
                    fontSize = 16.sp,
                    color = if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19),
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
                Text(
                    text = item.description,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    MerasaRestoTheme {
        MenuContent(
            isDarkMode = false,
            onBackClick = {},
            onItemClick = {}
        )
    }
}
