package com.udhipe.musemviewer.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udhipe.musemviewer.data.collection.CollectionRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val collectionRepository: CollectionRepository) : ViewModel() {
    private val mViewState = MutableLiveData<HomeViewState>().apply {
        value = HomeViewState(loading = true)
    }
    val viewState: LiveData<HomeViewState>
        get() = mViewState

    fun getCollectionList(page: Int) = viewModelScope.launch {
        try {
            val data = collectionRepository.getCollectionList(page)
            mViewState.value = mViewState.value?.copy(loading = false, error = null, data = data)
        } catch (ex: Exception) {
            mViewState.value = mViewState.value?.copy(loading = false, error = ex, data = null)
        }
    }
}