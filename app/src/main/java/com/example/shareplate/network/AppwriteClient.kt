package com.example.shareplate.network

import android.content.Context
import io.appwrite.Client
import io.appwrite.services.Account

object AppwriteClient {
    private const val ENDPOINT = "https://cloud.appwrite.io/v1"  // Your Appwrite endpoint
    private const val PROJECT_ID = "6780d5df002ce5d5c03e"       // Your project ID

    private var client: Client? = null

    fun initialize(context: Context) {
        if (client == null) {
            client = Client(context)
                .setEndpoint(ENDPOINT)
                .setProject(PROJECT_ID)
                .setSelfSigned(true) // Remove in production
        }
    }

    val account: Account
        get() {
            checkNotNull(client) { "AppwriteClient must be initialized first" }
            return Account(client!!)
        }
} 