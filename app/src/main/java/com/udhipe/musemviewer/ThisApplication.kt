package com.udhipe.musemviewer

import android.app.Application
import com.udhipe.musemviewer.data.collection.CollectionRemoteDataSource
import com.udhipe.musemviewer.data.collection.CollectionRepository
import com.udhipe.musemviewer.webservice.Network

class ThisApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val collectionService = Network.COLLECTION_SERVICE
        CollectionRepository.instance.apply {
            init(
                CollectionRemoteDataSource(collectionService)
            )
        }
    }
}