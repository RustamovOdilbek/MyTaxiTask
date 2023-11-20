package uz.innovation.mytaxitask.presentation.fragments


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mapbox.android.gestures.MoveGestureDetector
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.gestures.OnMoveListener
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorBearingChangedListener
import com.mapbox.maps.plugin.locationcomponent.OnIndicatorPositionChangedListener
import uz.innovation.mytaxitask.R
import uz.innovation.mytaxitask.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    private val onIndicatorBearingChangedListener =
        OnIndicatorBearingChangedListener { v ->
            binding.mapView.getMapboxMap().setCamera(CameraOptions.Builder().bearing(v).build())
        }

    private val onIndicatorPositionChangedListener =
        OnIndicatorPositionChangedListener { point ->
            binding.mapView.getMapboxMap()
                .setCamera(CameraOptions.Builder().center(point).zoom(20.0).build())
        }

    private val onMoveListener: OnMoveListener = object : OnMoveListener {
        override fun onMoveBegin(moveGestureDetector: MoveGestureDetector) {

        }

        override fun onMove(moveGestureDetector: MoveGestureDetector): Boolean {
            return false
        }

        override fun onMoveEnd(moveGestureDetector: MoveGestureDetector) {}
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mapView.getMapboxMap().loadStyleUri(Style.SATELLITE, object : Style.OnStyleLoaded {
            override fun onStyleLoaded(style: Style) {
                binding.mapView.getMapboxMap().setCamera(CameraOptions.Builder().zoom(20.0).build())
            }
        })
    }


}