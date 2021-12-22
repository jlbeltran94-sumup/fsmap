package com.example.fsmap

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.fsmap.data.models.PinData
import com.example.fsmaplibrary.mapinteractor.MapInteractor
import com.example.fsmaplibrary.utils.MarkerOptionMapper
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    @Inject
    lateinit var mapInteractor: MapInteractor

    @Inject
    lateinit var markerOptionMapper: MarkerOptionMapper<PinData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapActivityContent(this)
        }
    }

    override fun onMapReady(map: GoogleMap) {
        mapInteractor.googleMap = map
        mapInteractor.addData(markerOptionMapper.createMarkerOption(mockData), true)
        mapInteractor.addData(markerOptionMapper.createMarkerOptions(mockData2), true)
        mapInteractor.addData(markerOptionMapper.createMarkerOptions(mockData3), true)
    }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            id = R.id.map
        }
    }

    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}

@Composable
fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> throw IllegalStateException()
            }
        }
    }


@Composable
fun MapActivityContent(onMapReadyCallback: OnMapReadyCallback) {
    val mapView = rememberMapViewWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White)
    ) {
        AndroidView({ mapView }) { mapView ->
            mapView.getMapAsync(onMapReadyCallback)
        }
    }
}

val mockData = PinData(
    name = "test adelaide",
    type = "restaurant",
    lat = -34.92873,
    lng = 138.59995,
    phone = 123456789,
    address = "ADELAIDE"
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