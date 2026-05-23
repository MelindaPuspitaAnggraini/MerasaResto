package com.example.merasaresto.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.merasaresto.data.PreferenceManager
import com.example.merasaresto.model.RestaurantProfile
import com.example.merasaresto.ui.theme.MerasaRestoTheme

/**
 * Tampilan Edit Profil - Desain Elegan & Modern
 */
@Composable
fun EditProfileScreen(navController: NavController, preferenceManager: PreferenceManager) {
    val currentProfile = remember { preferenceManager.getProfile() }

    var name by remember { mutableStateOf(currentProfile.name) }
    var address by remember { mutableStateOf(currentProfile.address) }
    var description by remember { mutableStateOf(currentProfile.description) }
    var openingHours by remember { mutableStateOf(currentProfile.openingHours) }

    EditProfileContent(
        name = name,
        onNameChange = { name = it },
        address = address,
        onAddressChange = { address = it },
        description = description,
        onDescriptionChange = { description = it },
        openingHours = openingHours,
        onOpeningHoursChange = { openingHours = it },
        onSave = {
            preferenceManager.saveProfile(
                RestaurantProfile(name, address, description, openingHours)
            )
            navController.popBackStack()
        },
        onCancel = { navController.popBackStack() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileContent(
    name: String,
    onNameChange: (String) -> Unit,
    address: String,
    onAddressChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    openingHours: String,
    onOpeningHoursChange: (String) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    val isDarkMode = isSystemInDarkTheme()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Edit Profil", 
                        fontWeight = FontWeight.ExtraBold,
                        color = if (isDarkMode) Color.White else Color(0xFF5D1D19)
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack, 
                            contentDescription = "Batal",
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
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Input Fields dengan aksen dinamis
                val textFieldColors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19),
                    focusedLabelColor = if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19),
                    cursorColor = if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19),
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = { Text("Nama Restoran") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = textFieldColors
                )

                OutlinedTextField(
                    value = address,
                    onValueChange = onAddressChange,
                    label = { Text("Alamat") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = textFieldColors
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = onDescriptionChange,
                    label = { Text("Deskripsi") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    shape = RoundedCornerShape(16.dp),
                    colors = textFieldColors
                )

                OutlinedTextField(
                    value = openingHours,
                    onValueChange = onOpeningHoursChange,
                    label = { Text("Jam Buka") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = textFieldColors
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tombol Aksi Modern
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedButton(
                        onClick = onCancel,
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            width = 2.dp,
                            brush = SolidColor(if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19))
                        )
                    ) {
                        Text(
                            "Batal", 
                            fontSize = 16.sp, 
                            color = if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19), 
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = onSave,
                        modifier = Modifier
                            .weight(1f)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = if (isDarkMode) Color(0xFF8B433E) else Color(0xFF5D1D19))
                    ) {
                        Icon(Icons.Default.Save, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Simpan", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
    MerasaRestoTheme {
        EditProfileContent(
            name = "Merasa Resto",
            onNameChange = {},
            address = "Jl. Kertanegara No.176, Kota Malang",
            onAddressChange = {},
            description = "Restoran yang menghadirkan pengalaman kuliner dengan cita rasa nusantara dan menggugah selera.",
            onDescriptionChange = {},
            openingHours = "09:00 - 22:00",
            onOpeningHoursChange = {},
            onSave = {},
            onCancel = {}
        )
    }
}
