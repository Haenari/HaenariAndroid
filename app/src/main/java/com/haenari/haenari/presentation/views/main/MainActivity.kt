package com.haenari.haenari.presentation.views.main

import android.annotation.SuppressLint
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
import com.haenari.haenari.databinding.ActivityMainBinding
import com.haenari.haenari.presentation.base.activity.MVIActivity
import com.haenari.haenari.presentation.util.Locations
import com.haenari.haenari.presentation.util.Logs
import com.haenari.haenari.presentation.util.Permissions
import com.haenari.haenari.presentation.util.extension.gone
import com.haenari.haenari.presentation.util.extension.visible
import com.haenari.haenari.presentation.views.home.HomeViewModel
import com.haenari.haenari.presentation.views.weather.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity :
    MVIActivity<ActivityMainBinding, MainEvent, MainState>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val weatherViewModel: WeatherViewModel by viewModels()

    private val mainViewPagerAdapter = MainViewPagerAdapter(this)

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(
            this
        )
    }
    private val locationRequest =
        LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, TimeUnit.HOURS.toMillis(1)).build()
    private lateinit var locationCallback: LocationCallback

    override fun render(state: MainState) {
        bind {
            if (state.isLoading) {
                pbLoading.visible()
            } else {
                pbLoading.gone()
            }

            if (state.address.isNotEmpty()) {
                Logs.e(state.address)
                homeViewModel.receivedLocation(address = state.address)
                weatherViewModel.receivedLocation(address = state.address)
            }
        }
    }

    override fun initView() {
        bind {
            viewModel = this@MainActivity.viewModel

            vpMain.run {
                adapter = mainViewPagerAdapter
                isUserInputEnabled = false
            }

            bnvMain.setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.btnHome -> {
                        vpMain.currentItem = MainViewPagerAdapter.INDEX_HOME
                    }

                    R.id.btnWeather -> {
                        vpMain.currentItem = MainViewPagerAdapter.INDEX_WEATHER
                    }

                    R.id.btnWearing -> {
                        vpMain.currentItem = MainViewPagerAdapter.INDEX_WEARING
                    }

                    R.id.btnSettings -> {
                        vpMain.currentItem = MainViewPagerAdapter.INDEX_SETTINGS
                    }
                }
                true
            }
        }

        val address = intent.getStringExtra("address") ?: ""
        homeViewModel.receivedLocation(address = address)
        weatherViewModel.receivedLocation(address = address)
    }

    @SuppressLint("MissingPermission")
    fun requestLocation() {
        viewModel.loading()

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
}