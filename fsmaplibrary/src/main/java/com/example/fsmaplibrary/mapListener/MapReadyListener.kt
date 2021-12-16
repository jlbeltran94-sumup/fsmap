package com.example.fsmaplibrary.mapListener

import com.example.fsmaplibrary.data.PinData
import com.google.android.gms.maps.OnMapReadyCallback

interface MapReadyListener:OnMapReadyCallback {
    fun setNewData(pinData: List<PinData>)
    fun addData(pinData: List<PinData>, moveCamera: Boolean)
}