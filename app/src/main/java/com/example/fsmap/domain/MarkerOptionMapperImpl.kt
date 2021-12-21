package com.example.fsmap.domain

import com.example.fsmap.data.models.PinData
import com.example.fsmaplibrary.utils.MarkerIconResourceProvider
import com.example.fsmaplibrary.utils.MarkerOptionMapper
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MarkerOptionMapperImpl : MarkerOptionMapper<PinData> {

    override fun createMarkerOption(data: PinData): MarkerOptions =
        with(data) {
            MarkerOptions()
                .position(LatLng(lat, lng))
                .title(name)
                .snippet("$address $phone")
                .infoWindowAnchor(0.5F, 0.5F)
                .icon(
                    BitmapDescriptorFactory.fromResource(
                        MarkerIconResourceProvider.getIconForType(type)
                    )
                )
        }

    override fun createMarkerOptions(data: List<PinData>): List<MarkerOptions> =
        data.map { createMarkerOption(it) }
}