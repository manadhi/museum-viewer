package com.udhipe.musemviewer.data.collection

import com.udhipe.musemviewer.BuildConfig
import com.udhipe.musemviewer.webservice.CollectionService

class CollectionRemoteDataSource(private val collectionService: CollectionService) {
    private val apiKey = BuildConfig.API_KEY
    private val limit = 20

    suspend fun getCollectionList(page: Int): MutableList<Collection>? {
        val response = collectionService.getCollectionList(apiKey, limit, page)
        if (response.isSuccessful) return response.body()?.artObjects

        throw Exception("Sorry, an error occurred while requesting data, status error ${response.code()}")
    }

    suspend fun getSpecificCollection(objectNumber: String): Collection? {
        val response = collectionService.getCollectionById(objectNumber, apiKey)
        if (response.isSuccessful) return response.body()?.artObject

        throw Exception("Sorry, an error occurred while requesting data, status error ${response.code()}")
    }
}