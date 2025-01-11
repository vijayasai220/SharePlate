package com.example.shareplate.screens

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.*
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import org.osmdroid.util.GeoPoint
import com.example.shareplate.model.FoodDonation
import com.example.shareplate.components.FoodDonationDialog
import com.example.shareplate.R
import org.osmdroid.api.IGeoPoint

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onDonationSubmitted: (FoodDonation, Uri?) -> Unit
) {
    var mapView: MapView? by remember { mutableStateOf(null) }
    var myLocationOverlay: MyLocationNewOverlay? by remember { mutableStateOf(null) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var showDonationDialog by remember { mutableStateOf(false) }
    var selectedLocation by remember { mutableStateOf<GeoPoint?>(null) }

    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(
            factory = { context ->
                MapView(context).apply {
                    mapView = this
                    setMultiTouchControls(true)
                    controller.setZoom(15.0)

                    myLocationOverlay = MyLocationNewOverlay(this).apply {
                        enableMyLocation()
                        enableFollowLocation()
                        runOnFirstFix {
                            scope.launch {
                                controller.animateTo(myLocation)
                            }
                        }
                    }
                    overlays.add(myLocationOverlay)

                    setOnTouchListener { _, event ->
                        if (event.action == android.view.MotionEvent.ACTION_UP) {
                            val projection = projection
                            val geoPoint = projection.fromPixels(event.x.toInt(), event.y.toInt())
                            selectedLocation = geoPoint as? GeoPoint // Ensure correct type assignment
                            showDonationDialog = true
                        }
                        false
                    }
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Location Button
        FloatingActionButton(
            onClick = {
                scope.launch {
                    myLocationOverlay?.myLocation?.let { location ->
                        mapView?.controller?.animateTo(location)
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_my_location),
                contentDescription = "My Location"
            )
        }

        // Add Donation Button
        FloatingActionButton(
            onClick = {
                selectedLocation = myLocationOverlay?.myLocation?.let { location ->
                    // Explicitly convert IGeoPoint to GeoPoint
                    if (location is GeoPoint) location else GeoPoint(location.latitude, location.longitude)
                }
                showDonationDialog = true
            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "Add Donation"
            )
        }
    }

    if (showDonationDialog && selectedLocation != null) {
        FoodDonationDialog(
            onDismiss = { showDonationDialog = false },
            onSubmit = { donation, imageUri ->
                onDonationSubmitted(donation, imageUri)
                showDonationDialog = false
            },
            latitude = selectedLocation!!.latitude,
            longitude = selectedLocation!!.longitude
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            mapView?.onDetach()
        }
    }
}
