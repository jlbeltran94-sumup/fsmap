package com.example.fsmap

import com.example.fsmap.data.models.FakeService
import com.example.fsmap.data.models.PinData
import com.example.fsmaplibrary.mapinteractor.MapInteractor
import com.example.fsmaplibrary.utils.MarkerOptionMapper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MapViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @MockK
    lateinit var mapInteractor: MapInteractor

    @MockK
    lateinit var markerOptionMapper: MarkerOptionMapper<PinData>

    @MockK
    lateinit var fakeService: FakeService

    @MockK
    lateinit var googleMap: GoogleMap

    lateinit var mapViewModel: MapViewModel

    val markerOptionsList: List<MarkerOptions> = listOf(MarkerOptions())
    val pinData: List<PinData> = listOf(
        PinData(
            name = "test",
            type = "test",
            lat = 0.0,
            lng = 0.0,
            phone = 0,
            address = "test"
        )
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        every { markerOptionMapper.createMarkerOptions(any()) } returns markerOptionsList
        every { mapInteractor.googleMap } returns googleMap
        coEvery { fakeService.provideMockData() } returns pinData
        mapViewModel = MapViewModel(mapInteractor, markerOptionMapper, fakeService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `verify get data`() {
        mapViewModel.getData(googleMap)
        coVerify { mapInteractor setProperty "googleMap" value this@MapViewModelTest.googleMap }
        coVerify { fakeService.provideMockData() }
        coVerify { markerOptionMapper.createMarkerOptions(pinData) }
        coVerify { mapInteractor.addData(markerOptionsList, true) }
        confirmVerified(fakeService, markerOptionMapper, mapInteractor)
    }

}