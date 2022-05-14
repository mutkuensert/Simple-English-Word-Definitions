package com.mutkuensert.simpleenglishworddefinitions.module

import com.mutkuensert.simpleenglishworddefinitions.service.WordService
import com.mutkuensert.simpleenglishworddefinitions.util.Util
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    @Singleton
    fun provideWordService(): WordService{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(Util.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build().create(WordService::class.java)
    }
}