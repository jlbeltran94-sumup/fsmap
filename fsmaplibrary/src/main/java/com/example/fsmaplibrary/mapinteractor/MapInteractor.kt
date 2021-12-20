package com.example.fsmaplibrary.mapinteractor

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions

interface MapInteractor {
    var googleMap: GoogleMap?
    fun addData(data: MarkerOptions, moveCamera: Boolean)
    fun addData(data: List<MarkerOptions>, moveCamera: Boolean)
    fun clearData()
}