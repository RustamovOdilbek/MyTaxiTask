package uz.innovation.mytaxitask.data.source

import android.util.Log
import uz.innovation.mytaxitask.data.database.CurrentLocationDao
import uz.innovation.mytaxitask.data.model.CurrentLocation
import uz.innovation.mytaxitask.domain.datasource.LocalDataSource
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val currentLocationDao: CurrentLocationDao
): LocalDataSource {
    override suspend fun insertCurrentLocation(currentLocation: CurrentLocation) {
        currentLocationDao.insertCurrentLocation(currentLocation)
    }

    override suspend fun updateCurrentLocation(currentLocation: CurrentLocation) {
        currentLocationDao.updateCurrentLocation(currentLocation)
    }

    override suspend fun getCurrentLocations(): List<CurrentLocation> {
        Log.d("dsassafas", "getCurrentLocations: ${currentLocationDao.getCurrentLocations()}")
        return currentLocationDao.getCurrentLocations()
    }

    override suspend fun deleteCurrentLocations() {
        currentLocationDao.deleteCurrentLocations()
    }


}