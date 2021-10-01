package com.technorapper.technoraploader.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.technorapper.technoraploader.data.repository.MainActivityRepository
import com.technorapper.technoraploader.domain.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: MainActivityInteractor
) : ViewModel() {
    override fun onCleared() {

        super.onCleared()
    }


    private val _uiState: MutableLiveData<DataState> = MutableLiveData()
    val uiState: MutableLiveData<DataState> get() = _uiState

    private val _downloading: MutableLiveData<Boolean> = MutableLiveData()
    val downloading: LiveData<Boolean> = _downloading
    fun setStateEvent(mainStateEvent: MainStateEvent) {
        viewModelScope.launch {
            when (mainStateEvent) {
                is MainStateEvent.FetchImages -> {
                    repository.fetchImages(
                    ).collect { if (it != null) _uiState.postValue(it) }
                }

            }

        }


    }


}

sealed class MainStateEvent {

    object FetchImages : MainStateEvent()

}

