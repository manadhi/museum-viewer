package com.udhipe.musemviewer

import android.app.Application
import android.content.Context
import com.udhipe.musemviewer.data.SharedPreferencesManager
import com.udhipe.musemviewer.data.collection.CollectionRemoteDataSource
import com.udhipe.musemviewer.data.collection.CollectionRepository
import com.udhipe.musemviewer.webservice.Network

class ThisApplication : Application() {

    companion object {
        private lateinit var appContext: Context

        fun setThisAppContext(context: Context) {
            appContext = context
        }

        fun getThisAppContext(): Context {
            return appContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        setThisAppContext(applicationContext)

        SharedPreferencesManager.instance.apply {
            init()
        }

        val collectionService = Network.COLLECTION_SERVICE
        CollectionRepository.instance.apply {
            init(
                CollectionRemoteDataSource(collectionService)
            )
        }
    }
}