package com.mutkuensert.simpleenglishworddefinitions.service

import com.mutkuensert.simpleenglishworddefinitions.model.MainModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WordService {

    @GET("words")
    fun requestDefinition(
        @Query("sp") sp: String,
        @Query("md") md: String
    ): MainModel
}