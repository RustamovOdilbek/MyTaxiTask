package uz.innovation.mytaxitask.domain.use_case

import uz.innovation.mytaxitask.data.model.CurrentLocation
import uz.innovation.mytaxitask.domain.repository.MainRepository
import javax.inject.Inject

class UpdateLocationUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    suspend operator fun invoke(currentLocation: CurrentLocation) {
        mainRepository.updateCurrentLocation(currentLocation)
    }
}