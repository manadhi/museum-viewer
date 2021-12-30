package com.udhipe.musemviewer.ui.detail

import com.udhipe.musemviewer.data.collection.Collection
import java.lang.Exception

data class DetailViewState(
    val loading: Boolean = false,
    val error: Exception? = null,
    val data: Collection? = null
)