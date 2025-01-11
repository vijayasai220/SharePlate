package com.example.shareplate

import android.os.Bundle
<<<<<<< HEAD
=======
import android.widget.Toast
>>>>>>> 8586894 (added map and auth)
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shareplate.ui.theme.SharePlateTheme
=======
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shareplate.screens.HomeScreen
import com.example.shareplate.screens.LoginScreen
import com.example.shareplate.screens.SignupScreen
import com.example.shareplate.screens.SplashScreen
import com.example.shareplate.ui.theme.SharePlateTheme
import org.osmdroid.config.Configuration
import androidx.preference.PreferenceManager
import androidx.compose.runtime.Composable
import com.example.shareplate.network.AppwriteClient
>>>>>>> 8586894 (added map and auth)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD
=======

        // Initialize Appwrite client
        AppwriteClient.initialize(applicationContext)

        // Initialize OSMdroid configuration
        Configuration.getInstance().load(
            applicationContext,
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        )

>>>>>>> 8586894 (added map and auth)
        setContent {
            SharePlateTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
<<<<<<< HEAD
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "splash"
                    ) {
                        composable("splash") {
                            SplashScreen(
                                onSplashComplete = {
                                    navController.navigate("login") {
                                        popUpTo("splash") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("login") {
                            LoginScreen(
                                onSignUpClick = {
                                    navController.navigate("signup")
                                },
                                onLoginSuccess = {
                                    navController.navigate("main") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable("signup") {
                            Signupscreen(
                                onSignUpSuccess = {
                                    navController.navigate("main") {
                                        popUpTo("signup") { inclusive = true }
                                    }
                                },
                                onLoginClick = {
                                    navController.navigate("login") {
                                        popUpTo("signup") { inclusive = true }
                                    }
                                }
                            )
                        }
                    }
=======
                    MainNavigation()
>>>>>>> 8586894 (added map and auth)
                }
            }
        }
    }
<<<<<<< HEAD
=======
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onSplashComplete = {
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }
        composable("login") {
            LoginScreen(
                onSignUpClick = {
                    navController.navigate("signup")
                },
                onLoginSuccess = {
                    navController.navigate("main") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }
        composable("signup") {
            SignupScreen(
                onSignUpSuccess = {
                    navController.navigate("main") {
                        popUpTo("signup") { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate("login") {
                        popUpTo("signup") { inclusive = true }
                    }
                },
                onSkipClick = {
                    navController.navigate("main") {
                        popUpTo("signup") { inclusive = true }
                    }
                }
            )
        }
        composable("main") {
            HomeScreen(
                onDonationSubmitted = { donation, imageUri ->
                    Toast.makeText(
                        context,
                        "Donation submitted: ${donation.foodName}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            )
        }
    }
>>>>>>> 8586894 (added map and auth)
} 