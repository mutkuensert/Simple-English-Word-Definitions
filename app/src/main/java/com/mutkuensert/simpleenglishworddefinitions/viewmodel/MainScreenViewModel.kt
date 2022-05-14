package com.mutkuensert.simpleenglishworddefinitions.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mutkuensert.simpleenglishworddefinitions.model.MainModel
import com.mutkuensert.simpleenglishworddefinitions.service.WordService
import com.mutkuensert.simpleenglishworddefinitions.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainScreenViewModel @Inject constructor(val api: WordService) : ViewModel() {
    val response = MutableLiveData<Resource<List<MainModel>>>(Resource.standby(listOf(MainModel(null,null,null))))

    fun requestDefinition(word: String){
        viewModelScope.launch(Dispatchers.IO) {
            response.postValue(Resource.loading(null))//Post value is for changing live data from another thread.
            try {
                val request = api.requestDefinition(word,"d") //d means definition in api.
                if(request.isSuccessful){
                    request.body()?.let {
                        response.postValue(Resource.success(it))
                    }?: Resource.error("Error",null)
                }else{
                    response.postValue(Resource.error("Error",null))
                }
            }catch (e: Exception){
                println("Error on request.")
                response.postValue(Resource.error("Error",null))
            }
        }

    }
}