package com.example.shareplate.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shareplate.model.FoodDonation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import java.util.UUID
import com.example.shareplate.network.AppwriteClient
import kotlinx.coroutines.launch

@Composable
fun FoodDonationDialog(
    onDismiss: () -> Unit,
    onSubmit: (FoodDonation, Uri?) -> Unit,
    latitude: Double,
    longitude: Double
) {
    var foodName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var donorId by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    // Get current user ID
    LaunchedEffect(Unit) {
        try {
            val account = AppwriteClient.account.get()
            donorId = account?.id ?: ""
        } catch (e: Exception) {
            // Handle error getting user ID
            donorId = ""
        }
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add Food Donation") },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = foodName,
                    onValueChange = { foodName = it },
                    label = { Text("Food Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = quantity,
                    onValueChange = { quantity = it },
                    label = { Text("Quantity") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    maxLines = 5
                )

                Button(
                    onClick = { imagePicker.launch("image/*") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Select Image")
                }

                selectedImageUri?.let { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = "Selected food image",
                        modifier = Modifier
                            .size(200.dp)
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (foodName.isNotBlank() && quantity.toIntOrNull() != null) {
                        val donation = FoodDonation(
                            id = UUID.randomUUID().toString(),
                            foodName = foodName,
                            quantity = quantity.toIntOrNull() ?: 0,
                            description = description,
                            latitude = latitude,
                            longitude = longitude,
                            donorId = donorId,
                            timestamp = System.currentTimeMillis()
                        )
                        onSubmit(donation, selectedImageUri)
                        onDismiss()
                    }
                },
                enabled = foodName.isNotBlank() && quantity.toIntOrNull() != null
            ) {
                Text("Submit")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
} 