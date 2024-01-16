package com.haenari.haenari.presentation.views.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Geocoder
import android.os.Looper
import androidx.activity.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.haenari.haenari.AppConstants
import com.haenari.haenari.R
import com.haenari.haenari.databinding.ActivitySplashBinding
import com.haenari.haenari.presentation.base.activity.MVIActivity
import com.haenari.haenari.presentation.util.Locations
import com.haenari.haenari.presentation.util.Permissions
import com.haenari.haenari.presentation.views.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SplashActivity :
    MVIActivity<ActivitySplashBinding, SplashEvent, SplashState>(R.layout.activity_splash) {
    override val viewModel: SplashViewModel by viewModels()

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(
            this
        )
    }
    private val locationRequest =
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, TimeUnit.HOURS.toMillis(1)).build()
    private lateinit var locationCallback: LocationCallback

    override fun render(state: SplashState) {
        when {
            state.isSynced -> {
                startMainActivity(state.address)
            }
        }
    }

    override fun initView() {
        checkLocationPermission()
    }

    @SuppressLint("MissingPermission")
    private fun checkLocationPermission() {
        if (Permissions.isGranted(this, *Permissions.LOCATION_PERMISSIONS)) {
            locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    val latLng = if (result.locations.isEmpty()) {
                        (Locations.DEFAULT_LATITUDE to Locations.DEFAULT_LONGITUDE)
                    } else {
                        (result.locations[0].latitude to result.locations[0].longitude)
                    }
                    val address = getGeocodedAddress(latLng)
                    val coordinate = Locations.toCoordinates(latLng.first, latLng.second)

                    viewModel.requestWeather(coordinate, address)
                }
            }

            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } else {
            val latLng = (Locations.DEFAULT_LATITUDE to Locations.DEFAULT_LONGITUDE)
            val address = getGeocodedAddress(latLng)
            val coordinate = Locations.toCoordinates(latLng.first, latLng.second)

            viewModel.requestWeather(coordinate, address)
        }
    }

    private fun getGeocodedAddress(latLng: Pair<Double, Double>): String {
        val latitude = latLng.first
        val longitude = latLng.second
        val addressList = Geocoder(this).getFromLocation(latitude, longitude, 1) ?: emptyList()

        return if (addressList.isEmpty()) {
            AppConstants.DEFAULT_ADDRESS
        } else {
            Locations.getAddress(addressList[0])
        }
    }

    private fun startMainActivity(address: String) {
        startActivity(Intent(this@SplashActivity, MainActivity::class.java).apply {
            putExtra("address", address)
        })
        finish()
    }
}