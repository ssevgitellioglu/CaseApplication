package com.example.caseapplication.modules

import com.example.caseapplication.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}
