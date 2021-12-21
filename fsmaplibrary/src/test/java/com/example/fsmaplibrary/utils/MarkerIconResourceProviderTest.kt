package com.example.fsmaplibrary.utils

import com.example.fsmaplibrary.R
import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MarkerIconResourceProviderTest {

    @Before
    fun setUp() {
        mockkObject(MarkerIconResourceProvider)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `verify restaurant icon`() {
        assertEquals(R.raw.restaurant, MarkerIconResourceProvider.getIconForType("restaurant"))
    }

    @Test
    fun `verify shop icon`() {
        assertEquals(R.raw.shop, MarkerIconResourceProvider.getIconForType("shop"))
    }

    @Test
    fun `verify default icon`() {
        assertEquals(R.raw.pin, MarkerIconResourceProvider.getIconForType("any"))
    }
}
