package uz.innovation.mytaxitask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class CurrentLocation(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val time: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
)