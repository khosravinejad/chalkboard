package com.moryzaky.chalkboard.di

import com.moryzaky.chalkboard.presentation.persons.recyclerview.PersonsAdapter
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
class PersonListModule {
    @Singleton
    @Provides
    fun providePersonAdapter() = PersonsAdapter()
}