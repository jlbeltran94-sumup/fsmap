package com.example.fsmaplibrary.utils

import com.google.android.gms.maps.model.MarkerOptions

interface MarkerOptionMapper<T> {
    fun createMarkerOption(data: T): MarkerOptions
    fun createMarkerOptions(data: List<T>): List<MarkerOptions>
}