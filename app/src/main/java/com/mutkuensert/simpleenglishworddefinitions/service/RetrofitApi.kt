package com.mutkuensert.simpleenglishworddefinitions.service

import com.mutkuensert.simpleenglishworddefinitions.model.MainModel
import com.mutkuensert.simpleenglishworddefinitions.util.Util
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitApi {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(Util.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build().create(WordService::class.java)

    suspend fun requestDefinition(word: String): List<MainModel>{
        return api.requestDefinition(word,"d") //d means definition in api.
    }
}