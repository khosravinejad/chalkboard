package com.moryzaky.chalkboard.di

import com.moryzaky.chalkboard.data.repository.PersonRepositoryImpl
import com.moryzaky.chalkboard.data.source.local.ChalkBoardDatabase
import com.moryzaky.chalkboard.data.source.remote.ApiServices
import com.moryzaky.chalkboard.domain.repository.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun providePersonRepository(
        apiServices: ApiServices,
        database: ChalkBoardDatabase
    ): PersonRepository =
        PersonRepositoryImpl(apiServices, database)
}