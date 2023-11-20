package uz.innovation.mytaxitask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.innovation.mytaxitask.data.database.CurrentLocationDao
import uz.innovation.mytaxitask.data.source.LocalDataSourceImpl
import uz.innovation.mytaxitask.domain.datasource.LocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(
        currentLocationDao: CurrentLocationDao
    ): LocalDataSource = LocalDataSourceImpl(currentLocationDao)

}