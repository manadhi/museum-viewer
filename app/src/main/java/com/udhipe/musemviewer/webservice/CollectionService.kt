package com.udhipe.musemviewer.webservice

import com.udhipe.musemviewer.data.collection.CollectionListResponse
import com.udhipe.musemviewer.data.collection.CollectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionService {
    @GET("collection")
    suspend fun getCollectionList(
        @Query("key") key: String,
        @Query("ps") ps: Int,
        @Query("p") p: Int
    )
            : Response<CollectionListResponse>

    @GET("collection/{objectNumber}")
    suspend fun getCollectionById(
        @Path("objectNumber") objectNumber: String,
        @Query("key") key: String
    )
            : Response<CollectionResponse>
}