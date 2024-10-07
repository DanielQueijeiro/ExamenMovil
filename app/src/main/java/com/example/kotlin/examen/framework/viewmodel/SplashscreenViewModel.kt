package com.example.kotlin.examen.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashscreenViewModel: ViewModel() {
    val finishedLoading = MutableLiveData<Boolean>()

    fun onCreate(){
        finishedLoading.postValue(false)
        viewModelScope.launch {
            delay(35L)
            finishedLoading.postValue(true)
        }
    }
}