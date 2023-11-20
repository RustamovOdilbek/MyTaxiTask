package uz.innovation.mytaxitask.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.innovation.mytaxitask.data.model.CurrentLocation

@Database(entities = [CurrentLocation::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun currentLocationDao(): CurrentLocationDao
}