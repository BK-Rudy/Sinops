package com.example.assessment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {
    private val _nome = MutableLiveData<String>()
    val nome: LiveData<String> = _nome

    init{
        resetUsuario()
    }

    fun resetUsuario() {
        _nome.value = ""
    }

    fun setNome(value: String){
        _nome.value = value
    }
}