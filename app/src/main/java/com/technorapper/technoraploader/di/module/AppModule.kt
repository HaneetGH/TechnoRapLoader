package com.technorapper.technoraploader.di.module

import android.content.Context

import com.technorapper.technoraploader.core.TechnoRapLoader
import dagger.Module
import dagger.Provides

import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import okhttp3.CacheControl
import okhttp3.Interceptor

import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun technoRapLoader(@ApplicationContext app: Context): TechnoRapLoader? {


        return TechnoRapLoader.getInstance(app)
    }

    fun provideCacheInterceptor(maxDays: Int): Interceptor? {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())
            val cacheControl: CacheControl = CacheControl.Builder()
                .maxAge(maxDays, TimeUnit.DAYS)
                .build()
            response.newBuilder()
                .header("Cache-Control", cacheControl.toString())
                .build()
        }
    }
}