package com.example.kotlin.examen.framework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.examen.data.model.PersonajeObject
import com.example.kotlin.examen.domain.PersonajeListRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonajeViewModel : ViewModel() {
    val personajeObjectLiveData = MutableLiveData<PersonajeObject>()
    private val personajeListRequirement = PersonajeListRequirement()

    fun getPersonajeList(page: Int) {
        viewModelScope.launch {
            val result: PersonajeObject? = personajeListRequirement(page)
            CoroutineScope(Dispatchers.Main).launch {
                personajeObjectLiveData.postValue(result!!)
            }
        }
    }
}