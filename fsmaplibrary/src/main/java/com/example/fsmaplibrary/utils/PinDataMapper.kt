package com.example.fsmaplibrary.utils

import com.example.fsmaplibrary.data.PinData
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class PinDataMapper {

    fun pinDataToMarkerOptions(pinData: PinData): MarkerOptions =
        with(pinData) {
            MarkerOptions()
                .position(LatLng(lat, lng))
                .title(name)
                .snippet("$address $phone")
                .infoWindowAnchor(0.5F, 0.5F)
                .icon(BitmapDescriptorFactory.fromResource(MarkerIconResourceProvider.getIconForType(type)))
        }

    fun pinDataToMarkerOptions(pinData: List<PinData>): List<MarkerOptions> =
        pinData.map { pinDataToMarkerOptions(it) }
}