package com.example.shareplate

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.painter.Painter // Import Painter // Import PasswordVisualTransformation
import androidx.compose.material3.Icon // Import Icon
import androidx.compose.material3.Checkbox
import androidx.compose.material3.TextButton
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import com.example.shareplate.ui.theme.SharePlateTheme


@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "Login",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Enter Your Email") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Enter Your Password") },
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Remember Me and Forgot Password
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = rememberMe,
                    onCheckedChange = { rememberMe = it }
                )
                Text("Remember me")
            }
            TextButton(onClick = { /* Handle forgot password */ }) {
                Text("Forgot Password?")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(
            onClick = { /* Handle login */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log in")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Social Login Options
        Text("Or continue with")

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            SocialLoginButton(
                icon = painterResource(id = R.drawable.ic_google),
                text = "Google"
            )
            SocialLoginButton(
                icon = painterResource(id = R.drawable.ic_facebook),
                text = "Facebook"
            )
            SocialLoginButton(
                icon = painterResource(id = R.drawable.ic_linkedin),
                text = "LinkedIn"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign-Up Prompt
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Don't have an account?")
            TextButton(onClick = { /* Handle sign-up */ }) {
                Text("Sign up.")
            }
        }
    }
}@Composable
fun SocialLoginButton(
    icon: Painter,
    text: String
) {
    OutlinedButton(
        onClick = { /* Handle social login */ },
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.Blue),
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = Color.Unspecified, // Preserve the original colors of the Google logo
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = text,
                color = Color.Black, // Match text color to the design
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    SharePlateTheme {
        LoginScreen()
    }
}
