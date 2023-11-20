package uz.innovation.mytaxitask.data.repository

import uz.innovation.mytaxitask.data.model.CurrentLocation
import uz.innovation.mytaxitask.domain.datasource.LocalDataSource
import uz.innovation.mytaxitask.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): MainRepository {
    override suspend fun insertCurrentLocation(currentLocation: CurrentLocation) {
        localDataSource.insertCurrentLocation(currentLocation)
    }

    override suspend fun updateCurrentLocation(currentLocation: CurrentLocation) {
        localDataSource.updateCurrentLocation(currentLocation)
    }

    override suspend fun getCurrentLocations(): List<CurrentLocation> {
        return localDataSource.getCurrentLocations()
    }

    override suspend fun deleteCurrentLocations() {
        localDataSource.deleteCurrentLocations()
    }
}