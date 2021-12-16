package com.example.fsmaplibrary.mapListener

import android.util.Log
import com.example.fsmaplibrary.data.PinData
import com.example.fsmaplibrary.utils.PinDataMapper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap

class MapReadyListenerImpl : MapReadyListener {
    lateinit var map: GoogleMap
    val pinDataMapper: PinDataMapper = PinDataMapper()

    override fun setNewData(pinData: List<PinData>) {
        if (!checkMapReady()) {
            Log.d("MAP_LISTENER", "MAP IS NOT READY")
            dataList.add(MapOperation(OperationType.NEW, pinData))
            return
        }
        map.apply {
            clear()
            val pinDataToMarkerOptions = pinDataMapper.pinDataToMarkerOptions(pinData)
            pinDataToMarkerOptions.forEach {
                map.addMarker(it)
            }
            map.moveCamera(CameraUpdateFactory.newLatLng(pinDataToMarkerOptions.first().position))
        }
    }

    override fun addData(pinData: List<PinData>, moveCamera: Boolean) {
        if (!checkMapReady()) {
            Log.d("MAP_LISTENER", "MAP IS NOT READY")
            dataList.add(MapOperation(OperationType.ADD, pinData, moveCamera))
            return
        }
        map.apply {
            val pinDataToMarkerOptions = pinDataMapper.pinDataToMarkerOptions(pinData)
            pinDataToMarkerOptions.forEach {
                map.addMarker(it)
            }
            if (moveCamera) {
                map.moveCamera(CameraUpdateFactory.newLatLng(pinDataToMarkerOptions.first().position))
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        if (dataList.isNotEmpty()) {
            dataList.forEach {
                when (it.operationType) {
                    OperationType.ADD -> addData(it.data, it.moveCamera ?: false)
                    OperationType.NEW -> setNewData(it.data)
                }
            }
            dataList.clear()
        }
    }

    private fun checkMapReady(): Boolean {
        return this::map.isInitialized
    }

    private val dataList: MutableList<MapOperation> = mutableListOf()
}

data class MapOperation(val operationType: OperationType, val data: List<PinData>, val moveCamera: Boolean? = null)

enum class OperationType {
    NEW,
    ADD
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