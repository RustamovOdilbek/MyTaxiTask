package uz.innovation.mytaxitask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.innovation.mytaxitask.data.repository.MainRepositoryImpl
import uz.innovation.mytaxitask.domain.datasource.LocalDataSource
import uz.innovation.mytaxitask.domain.repository.MainRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        localDataSource: LocalDataSource
    ): MainRepository = MainRepositoryImpl(localDataSource)

}