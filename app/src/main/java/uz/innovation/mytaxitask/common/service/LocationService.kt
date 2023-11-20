package uz.innovation.mytaxitask.common.service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.innovation.mytaxitask.R
import uz.innovation.mytaxitask.data.model.CurrentLocation
import uz.innovation.mytaxitask.domain.use_case.UpdateLocationUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject


@AndroidEntryPoint
class LocationService: Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    @Inject
    lateinit var updateLocationUseCase: UpdateLocationUseCase

    override fun onCreate() {
        super.onCreate()
        locationClient = DefaultLocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        val notification = NotificationCompat.Builder(this, "location")
            .setContentTitle("Current location...")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.ic_mytaxi)
            .setOngoing(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        locationClient
            .getLocationUpdates(100L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                val lat = location.latitude
                val long = location.longitude
                val currentTime: String =
                    SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                val currentLocation = CurrentLocation(time = currentTime, latitude = lat, longitude = long)
                Log.d("dssadfsaf", "start: ${currentLocation}")
                updateLocationUseCase(currentLocation)

                val updatedNotification = notification.setContentText(
                    "Time: $currentTime, Location: (${lat.toString().takeLast(3)}, ${long.toString().takeLast(3)})"
                )
                notificationManager.notify(1, updatedNotification.build())
            }
            .launchIn(serviceScope)

        startForeground(1, notification.build())
    }

    private fun stop() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }
}