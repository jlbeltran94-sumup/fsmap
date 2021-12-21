package com.example.fsmaplibrary.mapinteractor

import com.example.fsmaplibrary.MapNotDefinedException
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions

class MapInteractorImpl(override var googleMap: GoogleMap?) : MapInteractor {

    override fun addData(data: MarkerOptions, moveCamera: Boolean) {
        googleMap?.apply {
            addMarker(data)
            if (moveCamera) {
                moveCamera(CameraUpdateFactory.newLatLng(data.position))
            }
        } ?: kotlin.run {
            throw MapNotDefinedException("Map is not set")
        }
    }

    override fun addData(data: List<MarkerOptions>, moveCamera: Boolean) {
        googleMap?.apply {
            data.forEach {
                addMarker(it)
            }
            if (moveCamera) {
                data.firstOrNull()?.position?.let {
                    moveCamera(CameraUpdateFactory.newLatLng(it))
                }
            }
        } ?: kotlin.run {
            throw MapNotDefinedException("Map is not set")
        }
    }

    override fun clearData() {
        googleMap?.clear() ?: kotlin.run {
            throw MapNotDefinedException("Map is not set")
        }
    }
}