package com.udhipe.musemviewer.webservice

import com.udhipe.musemviewer.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    private const val BASE_URL = BuildConfig.BASE_URL

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val COLLECTION_SERVICE: CollectionService = client.create(CollectionService::class.java)
}