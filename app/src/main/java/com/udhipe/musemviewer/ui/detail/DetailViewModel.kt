package com.udhipe.musemviewer.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udhipe.musemviewer.data.collection.CollectionRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val collectionRepository: CollectionRepository) : ViewModel() {
    private val mViewState = MutableLiveData<DetailViewState>().apply {
        value = DetailViewState(loading = true)
    }
    val viewState: LiveData<DetailViewState>
        get() = mViewState

    fun getSpecificCollection(objectNumber: String) = viewModelScope.launch {
        try {
            val data = collectionRepository.getCollection(objectNumber)
            mViewState.value = mViewState.value?.copy(loading = false, error = null, data = data)
        } catch (ex: Exception) {
            mViewState.value = mViewState.value?.copy(loading = false, error = ex, data = null)
        }
    }
}