package com.anestevez.gigirestaurants.core

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import com.anestevez.gigirestaurants.core.location.LocationDataSource
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.resume

class PlayServicesLocationDataSource @Inject constructor(application: Application) :
    LocationDataSource {

    companion object {
        const val DEFAULT_LATLONG = "34.999139, 135.753528"
    }

    private val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    @SuppressLint("MissingPermission")
    override suspend fun getLastLatLong(): String = withContext(Dispatchers.IO) {
        suspendCancellableCoroutine { continuation ->
            fusedLocationProviderClient.lastLocation.addOnCompleteListener {
                continuation.resume(
                    try {
                        it.result.toLatLong()
                    } catch (throwable: Throwable) {
                        Timber.d(throwable)
                        DEFAULT_LATLONG
                    }
                )
            }
        }
    }

    private fun Location?.toLatLong(): String {
        if (this == null) {
            return DEFAULT_LATLONG
        }
        return "${this.latitude}, ${this.longitude}"
    }

}

