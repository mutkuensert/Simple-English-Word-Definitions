package com.mutkuensert.simpleenglishworddefinitions.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutkuensert.simpleenglishworddefinitions.model.MainModel
import com.mutkuensert.simpleenglishworddefinitions.service.RetrofitApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainScreenViewModel: ViewModel() {
    val response = MutableLiveData<MainModel>()
    val retrofit = RetrofitApi()

    fun requestDefinition(word: String){
        viewModelScope.launch(Dispatchers.IO) {
            response.postValue(retrofit.requestDefinition(word)) //Post value is for changing live data from another thread.
        }

    }
}