package com.example.fsmaplibrary.mapinteractor

import com.example.fsmaplibrary.MapNotDefinedException
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.MarkerOptions
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

class MapInteractorImplTest {

    @MockK(relaxed = true)
    lateinit var googleMap: GoogleMap

    @MockK(relaxed = true)
    lateinit var markerOptions: MarkerOptions

    @MockK
    lateinit var cameraUpdate: CameraUpdate

    val mapInteractor = MapInteractorImpl(null)


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockkStatic(CameraUpdateFactory::class)
        every { CameraUpdateFactory.newLatLng(any()) } returns cameraUpdate

    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `verify exception is thrown when map is not defined and try to add a marker`() {
        assertFailsWith(MapNotDefinedException::class) {
            mapInteractor.addData(markerOptions, true)
        }
    }

    @Test
    fun `verify exception is thrown when map is not defined and try to add a list of markers`() {
        assertFailsWith(MapNotDefinedException::class) {
            mapInteractor.addData(listOf(markerOptions), true)
        }
    }

    @Test
    fun `verify exception is thrown when map is not defined and try to clear data`() {
        assertFailsWith(MapNotDefinedException::class) {
            mapInteractor.clearData()
        }
    }

    @Test
    fun `verify marker is added and camera moves`() {
        mapInteractor.googleMap = googleMap
        mapInteractor.addData(markerOptions, true)
        verify { googleMap.addMarker(markerOptions) }
        verify { googleMap.moveCamera(any()) }
        confirmVerified(googleMap)
    }

    @Test
    fun `verify marker is added and camera does not move`() {
        mapInteractor.googleMap = googleMap
        mapInteractor.addData(markerOptions, false)
        verify { googleMap.addMarker(markerOptions) }
        verify(inverse = true) { googleMap.moveCamera(any()) }
        confirmVerified(googleMap)
    }

    @Test
    fun `verify list of markers is added and camera moves`() {
        mapInteractor.googleMap = googleMap
        mapInteractor.addData(listOf(markerOptions), true)
        verify(atLeast = 1) { googleMap.addMarker(markerOptions) }
        verify { googleMap.moveCamera(any()) }
        confirmVerified(googleMap)
    }

    @Test
    fun `verify list of markers is added and camera does not move`() {
        mapInteractor.googleMap = googleMap
        mapInteractor.addData(listOf(markerOptions), false)
        verify(atLeast = 1) { googleMap.addMarker(markerOptions) }
        verify(inverse = true) { googleMap.moveCamera(any()) }
        confirmVerified(googleMap)
    }

    @Test
    fun `clear data is called`() {
        mapInteractor.googleMap = googleMap
        mapInteractor.clearData()
        verify { googleMap.clear() }
        confirmVerified(googleMap)
    }
}