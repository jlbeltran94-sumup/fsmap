package com.example.fsmap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fsmap.data.models.FakeService
import com.example.fsmap.data.models.PinData
import com.example.fsmaplibrary.mapinteractor.MapInteractor
import com.example.fsmaplibrary.utils.MarkerOptionMapper
import com.google.android.gms.maps.GoogleMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapInteractor: MapInteractor,
    private val markerOptionMapper: MarkerOptionMapper<PinData>,
    private val fakeService: FakeService
) : ViewModel() {

    fun getData(googleMap: GoogleMap) {
        mapInteractor.googleMap = googleMap
        viewModelScope.launch {
            val data = fakeService.provideMockData()
            val markerOptions = markerOptionMapper.createMarkerOptions(data)
            mapInteractor.addData(markerOptions, true)
        }
    }

}