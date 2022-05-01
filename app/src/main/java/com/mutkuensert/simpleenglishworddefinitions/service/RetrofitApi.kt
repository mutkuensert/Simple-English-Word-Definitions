package com.mutkuensert.simpleenglishworddefinitions.service

import com.mutkuensert.simpleenglishworddefinitions.model.MainModel
import com.mutkuensert.simpleenglishworddefinitions.util.Util
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitApi {
    private val api = Retrofit.Builder()
        .baseUrl(Util.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(WordService::class.java)

    suspend fun requestDefinition(word: String): MainModel{
        return api.requestDefinition(word,"d") //d means definition in api.
    }
}