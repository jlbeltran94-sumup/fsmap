package com.example.fsmap.di

import com.example.fsmap.data.models.PinData
import com.example.fsmap.domain.MarkerOptionMapperImpl
import com.example.fsmaplibrary.mapinteractor.MapInteractor
import com.example.fsmaplibrary.mapinteractor.MapInteractorImpl
import com.example.fsmaplibrary.utils.MarkerOptionMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    fun provideMapInteractor(): MapInteractor = MapInteractorImpl(null)

    @Provides
    fun provideMarkerOptionMapper(): MarkerOptionMapper<PinData> = MarkerOptionMapperImpl()

}