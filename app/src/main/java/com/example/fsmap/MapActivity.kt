package com.example.fsmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fsmap.data.models.PinData
import com.example.fsmap.domain.MarkerOptionMapperImpl
import com.example.fsmaplibrary.mapinteractor.*
import com.example.fsmaplibrary.utils.MarkerOptionMapper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mapInteractor: MapInteractor = MapInteractorImpl(null)
    private val markerOptionMapper: MarkerOptionMapper<PinData> by lazy { MarkerOptionMapperImpl() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        mapInteractor.googleMap = map
        mapInteractor.addData(markerOptionMapper.createMarkerOptions(mockData), true)
        mapInteractor.addData(markerOptionMapper.createMarkerOptions(mockData2), true)
        mapInteractor.addData(markerOptionMapper.createMarkerOptions(mockData3), true)
    }
}

val mockData = listOf<PinData>(
    PinData(
        name = "test adelaide",
        type = "restaurant",
        lat = -34.92873,
        lng = 138.59995,
        phone = 123456789,
        address = "ADELAIDE"
    )
)
val mockData2 = listOf<PinData>(
    PinData(
        name = "test sidney",
        type = "shop",
        lat = -33.87365,
        lng = 151.20689,
        phone = 123456789,
        address = "SIDNEY"
    )
)
val mockData3 = listOf<PinData>(
    PinData(
        name = "test melbourne",
        type = "def",
        lat = -37.81319,
        lng = 144.96298,
        phone = 123456789,
        address = "MELBOURNE"
    )
)