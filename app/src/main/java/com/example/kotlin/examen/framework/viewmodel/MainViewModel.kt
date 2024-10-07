package com.example.kotlin.examen.framework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int> = _currentPage

    private val totalPages = 6 // Número fijo de páginas

    init {
        _currentPage.value = 1
    }

    fun nextPage() {
        if (_currentPage.value!! < totalPages) {
            _currentPage.value = _currentPage.value!! + 1
        }
    }

    fun previousPage() {
        if (_currentPage.value!! > 1) {
            _currentPage.value = _currentPage.value!! - 1
        }
    }

    fun canGoNext(): Boolean {
        return _currentPage.value!! < totalPages
    }

    fun canGoPrevious(): Boolean {
        return _currentPage.value!! > 1
    }
}