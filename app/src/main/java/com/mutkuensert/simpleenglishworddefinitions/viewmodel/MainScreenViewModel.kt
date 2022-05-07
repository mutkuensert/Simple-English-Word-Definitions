package com.mutkuensert.simpleenglishworddefinitions.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutkuensert.simpleenglishworddefinitions.model.MainModel
import com.mutkuensert.simpleenglishworddefinitions.service.RetrofitApi
import com.mutkuensert.simpleenglishworddefinitions.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainScreenViewModel: ViewModel() {
    val response = MutableLiveData<Resource<List<MainModel>>>(Resource.standby(listOf(MainModel(null,null,null))))
    private val retrofit = RetrofitApi()

    fun requestDefinition(word: String){
        viewModelScope.launch(Dispatchers.IO) {
            response.postValue(Resource.loading(null))//Post value is for changing live data from another thread.
            response.postValue(retrofit.requestDefinition(word)) //Returns Resource.status.Status.SUCCESS or ERROR
        }

    }
}