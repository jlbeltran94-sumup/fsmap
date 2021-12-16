package com.example.fsmap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fsmaplibrary.mapListener.*
import com.google.android.gms.maps.SupportMapFragment

class MapActivity : AppCompatActivity() {
    private var mapReadyListenerImpl: MapReadyListener = MapReadyListenerImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(mapReadyListenerImpl)
        mapReadyListenerImpl.setNewData(mockData)
        mapReadyListenerImpl.addData(mockData2,true)
        mapReadyListenerImpl.addData(mockData3,true)
    }
}