package com.abernikov.multiplatformapp.android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val apiRepository: ApiRepository) :
    ViewModel() {

    private val _state = MutableLiveData<StateMainVM>(StateMainVM.Loading)
    val state: LiveData<StateMainVM>
        get() = _state

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiRepository.getMovie()
            if (response.isSuccessful) {
                if (response.body() != null) _state.postValue(StateMainVM.Success(response.body()!!))
                else _state.postValue(StateMainVM.Error)
            }
        }
    }
}