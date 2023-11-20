package uz.innovation.mytaxitask.presentation.fragments.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.innovation.mytaxitask.common.Resource
import uz.innovation.mytaxitask.data.model.CurrentLocation
import uz.innovation.mytaxitask.domain.use_case.GetCurrentLocationUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase
): ViewModel() {

    private val _currentLocation = MutableStateFlow<Resource<List<CurrentLocation>>>(Resource.EMPTY)
    val currentLocation = _currentLocation


    fun getCurrentLocation() = viewModelScope.launch {
        _currentLocation.value = Resource.LOADING
        _currentLocation.value = getCurrentLocationUseCase()
    }


}