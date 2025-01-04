package com.example.shareplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shareplate.ui.theme.SharePlateTheme
import com.example.shareplate.R
import kotlinx.coroutines.delay
import androidx.navigation.compose.rememberNavController


class Splashscreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SharePlateTheme {
                // Set up NavHost for navigation
                NavHostExample()
            }
        }
    }
}

@Composable
fun NavHostExample() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true } // Pop up splash screen after navigation
                }
            })
        }
        composable("login") {
            LoginScreen() // LoginScreen is your existing login code
        }
    }
}

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    // Use LaunchedEffect to perform a side effect (timeout delay)
    LaunchedEffect(Unit) {
        delay(3000) // 3-second delay
        onTimeout() // Call the timeout function to navigate to the next screen
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // Add your app's logo or image
            Image(
                painter = painterResource(id = R.drawable.green2),
                contentDescription = "App Logo",
                modifier = Modifier.size(150.dp) // Adjust size as needed
            )

            Spacer(modifier = Modifier.height(16.dp))

            // App name or welcome text
            BasicText(
                text = "SharePlate",
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SharePlateTheme {
        SplashScreen(onTimeout = {})
    }
}
