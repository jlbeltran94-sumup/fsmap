package com.example.fsmap.domain

import com.example.fsmap.data.models.PinData
import com.example.fsmaplibrary.utils.MarkerOptionMapper
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkStatic
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MarkerOptionMapperImplTest {

    @MockK
    lateinit var defaultIcon: BitmapDescriptor



    private val markerOptionMapper: MarkerOptionMapper<PinData> = MarkerOptionMapperImpl()
    private val data = PinData(
        name = "test",
        type = "test",
        lat = 0.0,
        lng = 0.0,
        phone = 0,
        address = "test"
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mockkStatic(BitmapDescriptorFactory::class)
        every { BitmapDescriptorFactory.fromResource(any()) } returns defaultIcon
    }

    @Test
    fun `verify MarkerOptions is created correctly from PinData`() {
        val markerOptions = markerOptionMapper.createMarkerOption(data)
        assertMarkerOptions(markerOptions)
    }

    @Test
    fun `verify list of MarkerOptions is created correctly from list of PinData`() {
        val markerOptionsList = markerOptionMapper.createMarkerOptions(listOf(data))
        val markerOptions = markerOptionsList.first()
        assertMarkerOptions(markerOptions)
    }

    private fun assertMarkerOptions(markerOptions: MarkerOptions) {
        val delta = 0.1
        assertEquals(data.name, markerOptions.title)
        assertEquals(data.lat, markerOptions.position.latitude, delta)
        assertEquals(data.lng, markerOptions.position.longitude, delta)
        assertEquals("${data.address} ${data.phone}", markerOptions.snippet)
        assertEquals(defaultIcon, markerOptions.icon)
    }
}