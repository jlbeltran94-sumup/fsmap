package com.example.fsmap.data.models

import kotlinx.coroutines.delay
import javax.inject.Inject

class FakeService @Inject constructor() {

    suspend fun provideMockData(): List<PinData> {
        delay(1000)
        return mockData
    }
}

val mockData = listOf<PinData>(
    PinData(
        name = "test sidney",
        type = "shop",
        lat = -33.87365,
        lng = 151.20689,
        phone = 123456789,
        address = "SIDNEY"
    ),
    PinData(
        name = "test adelaide",
        type = "restaurant",
        lat = -34.92873,
        lng = 138.59995,
        phone = 123456789,
        address = "ADELAIDE"
    ),
    PinData(
        name = "test melbourne",
        type = "def",
        lat = -37.81319,
        lng = 144.96298,
        phone = 123456789,
        address = "MELBOURNE"
    )
)