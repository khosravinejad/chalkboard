package com.moryzaky.chalkboard.di

import android.app.Application
import androidx.room.Room
import com.moryzaky.chalkboard.data.source.local.ChalkBoardDatabase
import com.moryzaky.chalkboard.data.source.local.dao.PersonDao
import com.moryzaky.chalkboard.data.source.local.dao.PersonRemoteKeyDao
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
class DatabaseModule {

    @Provides
    @Singleton
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return ChalkBoardDatabase.DB_NAME
    }

    @Provides
    @Singleton
    fun provideDatabase(
        @DatabaseInfo dbName: String,
        application: Application
    ): ChalkBoardDatabase {
        return Room.databaseBuilder(application, ChalkBoardDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePersonDAO(database: ChalkBoardDatabase): PersonDao {
        return database.personDao
    }

    @Provides
    @Singleton
    fun providePersonRemoteKeyDAO(database: ChalkBoardDatabase): PersonRemoteKeyDao {
        return database.personRemoteKeyDao
    }
}