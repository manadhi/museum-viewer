package com.udhipe.musemviewer.ui.home

import com.udhipe.musemviewer.data.collection.Collection
import java.lang.Exception

data class HomeViewState(
    val loading: Boolean = false,
    val error: Exception? = null,
    val data: MutableList<Collection>? = null
)