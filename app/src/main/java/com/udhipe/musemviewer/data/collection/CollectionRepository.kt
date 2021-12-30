package com.udhipe.musemviewer.data.collection

class CollectionRepository {
    companion object {
        val instance by lazy { CollectionRepository() }
    }

    private var remoteDataSource: CollectionRemoteDataSource? = null

    fun init(remoteDataSource: CollectionRemoteDataSource) {
        this.remoteDataSource = remoteDataSource
    }

    suspend fun getCollectionList(page: Int): MutableList<Collection>? {
        return remoteDataSource?.getCollectionList(page)
    }

    fun getCollection(objectNumber: String): Collection? {
        return null
    }
}