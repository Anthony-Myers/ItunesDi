package com.example.gm_challenge.di

import com.example.gm_challenge.service.ArtistApi
import com.example.gm_challenge.util.DateAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://itunes.apple.com/"

    @Singleton
    @Provides
    fun provideMoshi() : Moshi = Moshi.Builder().add(DateAdapter).build()

    @Singleton
    @Provides
    fun provideArtistApi(moshi: Moshi): ArtistApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(ArtistApi::class.java)
}