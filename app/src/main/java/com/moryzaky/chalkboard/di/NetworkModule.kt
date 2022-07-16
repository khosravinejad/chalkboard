package com.moryzaky.chalkboard.di

import com.moryzaky.chalkboard.BuildConfig
import com.moryzaky.chalkboard.data.source.remote.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Morteza Khosravinejad on 16/07/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {

        val okHttpBuilder = okHttpClient.newBuilder()
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpBuilder.addInterceptor(httpLoggingInterceptor)
        }

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .baseUrl(ApiServices.BASE_URL)
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit.Builder): ApiServices =
        retrofit.build().create(ApiServices::class.java)
}