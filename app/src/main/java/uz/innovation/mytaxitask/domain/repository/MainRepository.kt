package uz.innovation.mytaxitask.domain.repository

import uz.innovation.mytaxitask.data.model.CurrentLocation

interface MainRepository {
    suspend fun insertCurrentLocation(currentLocation: CurrentLocation)

    suspend fun updateCurrentLocation(currentLocation: CurrentLocation)

    suspend fun getCurrentLocations(): List<CurrentLocation>

    suspend fun deleteCurrentLocations()
}