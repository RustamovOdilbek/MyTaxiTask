package uz.innovation.mytaxitask.domain.datasource

import uz.innovation.mytaxitask.data.model.CurrentLocation

interface LocalDataSource {
    suspend fun insertCurrentLocation(currentLocation: CurrentLocation)

    suspend fun updateCurrentLocation(currentLocation: CurrentLocation)

    suspend fun getCurrentLocations(): List<CurrentLocation>

    suspend fun deleteCurrentLocations()
}