package uz.innovation.mytaxitask.domain.use_case

import uz.innovation.mytaxitask.common.Resource
import uz.innovation.mytaxitask.data.model.CurrentLocation
import uz.innovation.mytaxitask.domain.repository.MainRepository
import java.io.IOException
import javax.inject.Inject

class GetCurrentLocationUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): Resource<List<CurrentLocation>> =
        try {
            Resource.SUCCESS(mainRepository.getCurrentLocations())
        } catch (e: IOException) {
            Resource.ERROR(e.localizedMessage)
        }
}