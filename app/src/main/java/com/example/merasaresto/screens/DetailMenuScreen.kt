package com.example.merasaresto.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.merasaresto.data.PreferenceManager
import com.example.merasaresto.model.MenuItem
import com.example.merasaresto.model.dummyMenu
import com.example.merasaresto.ui.theme.MerasaRestoTheme
import androidx.compose.ui.tooling.preview.Preview

/**
 * Tampilan Detail Menu - Desain Elegan & Modern
 */
@Composable
fun DetailMenuScreen(menuId: String?, navController: NavController, preferenceManager: PreferenceManager) {
    val menu = dummyMenu.find { it.id == menuId }
    val isDarkMode = preferenceManager.isDarkMode()

    DetailMenuContent(
        menu = menu,
        isDarkMode = isDarkMode,
        onBackClick = { navController.popBackStack() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMenuContent(menu: MenuItem?, isDarkMode: Boolean, onBackClick: () -> Unit) {
    var rating by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Detail Menu", 
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
                    containerColor = if (isDarkMode) Color(0xFF3E1411) else Color(0xFF5D1D19).copy(alpha = 0.15f)
                )
            )
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
            if (menu != null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Container Visual Menu yang Modern
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(280.dp)
                            .clip(RoundedCornerShape(32.dp)),
                        color = MaterialTheme.colorScheme.surface,
                        shadowElevation = 4.dp
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(if (isDarkMode) Color(0xFF8B433E).copy(alpha = 0.1f) else Color(0xFF5D1D19).copy(alpha = 0.05f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = menu.imageRes),
                                contentDescription = menu.name,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    // Nama Menu
                    Text(
                        text = menu.name,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center
                    )
                    
                    // Harga dengan Aksen Brick Red
                    Text(
                        text = menu.price,
                        fontSize = 26.sp,
                        color = if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19),
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    // Rating Interaktif yang Elegan
                    Row(
                        modifier = Modifier.padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(5) { index ->
                            val isSelected = index < rating
                            IconButton(
                                onClick = { rating = index + 1 },
                                modifier = Modifier.size(48.dp)
                            ) {
                                Icon(
                                    imageVector = if (isSelected) Icons.Default.Star else Icons.Outlined.StarBorder,
                                    contentDescription = null,
                                    tint = if (isSelected) (if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19)) else Color.LightGray,
                                    modifier = Modifier.size(32.dp)
                                )
                            }
                        }
                    }

                    // Deskripsi dengan Tipografi Bersih
                    Text(
                        text = menu.description,
                        fontSize = 16.sp,
                        lineHeight = 26.sp,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Tombol Kembali Modern
                    Button(
                        onClick = onBackClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19))
                    ) {
                        Text("Kembali ke Menu", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Menu tidak ditemukan!", color = if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailMenuPreview() {
    MerasaRestoTheme {
        DetailMenuContent(
            menu = dummyMenu[0],
            isDarkMode = false,
            onBackClick = {}
        )
    }
}
