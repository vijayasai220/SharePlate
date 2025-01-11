package com.example.shareplate.model

data class FoodDonation(
    val id: String = "",
    val foodName: String,
    val quantity: Int,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val donorId: String,
    val timestamp: Long = System.currentTimeMillis()
) 