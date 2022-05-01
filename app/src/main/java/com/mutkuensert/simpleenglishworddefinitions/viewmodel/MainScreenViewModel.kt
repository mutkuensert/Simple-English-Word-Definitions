package com.mutkuensert.simpleenglishworddefinitions.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mutkuensert.simpleenglishworddefinitions.model.MainModel

class MainScreenViewModel: ViewModel() {
    val response = MutableLiveData<MainModel>()

    fun requestDefinition(word: String){

    }
}