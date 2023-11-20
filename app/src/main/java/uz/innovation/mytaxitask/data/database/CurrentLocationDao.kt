package uz.innovation.mytaxitask.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import uz.innovation.mytaxitask.data.model.CurrentLocation

@Dao
interface CurrentLocationDao {
    @Transaction
    suspend fun updateCurrentLocation(currentLocation: CurrentLocation) {
        currentLocation.let {
            deleteCurrentLocations()
            insertCurrentLocation(it)
        }
    }

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCurrentLocation(currentLocation: CurrentLocation)

    @Query("DELETE FROM location")
    suspend fun deleteCurrentLocations()


    @Query("SELECT * FROM location ORDER BY time")
    suspend fun getCurrentLocations(): List<CurrentLocation>
}