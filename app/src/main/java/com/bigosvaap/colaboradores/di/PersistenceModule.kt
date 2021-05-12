package com.bigosvaap.colaboradores.di

import android.app.Application
import androidx.room.Room
import com.bigosvaap.colaboradores.persistance.AppDatabase
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, AppDatabase::class.java, "ColaboradoresDatabase")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideEmpleadoDao(appDatabase: AppDatabase) = appDatabase.empleadosDao()

    @Provides
    @Singleton
    fun provideMoshi() = Moshi.Builder().build()



}