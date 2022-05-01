package com.mutkuensert.simpleenglishworddefinitions.service

import com.mutkuensert.simpleenglishworddefinitions.util.Util
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitApi {
    private val api = Retrofit.Builder()
        .baseUrl(Util.baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(WordService::class.java)
}