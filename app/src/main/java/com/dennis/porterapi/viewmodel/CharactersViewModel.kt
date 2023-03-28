package com.dennis.porterapi.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dennis.porterapi.data.Characters
import com.dennis.porterapi.network.PotterApi
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    private val _characters = MutableLiveData<ArrayList<Characters>>()
    val characters:LiveData<ArrayList<Characters>> = _characters

    private fun fetchAllCharacters(){
        viewModelScope.launch {
                val response = PotterApi.retrofitService.getAllCharacters()
            if (response.isSuccessful){
                _characters.value = response.body()
            }
            else {
                throw Exception("Error : ${response.errorBody()}")
            }


        }
    }
    init {
        fetchAllCharacters()
    }

}