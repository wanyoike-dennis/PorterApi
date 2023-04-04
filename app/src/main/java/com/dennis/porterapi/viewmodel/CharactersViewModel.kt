package com.dennis.porterapi.viewmodel


import androidx.lifecycle.*
import com.dennis.porterapi.data.Characters
import com.dennis.porterapi.network.PotterApi
import kotlinx.coroutines.launch


class CharactersViewModel : ViewModel() {

    private val _characters = MutableLiveData<ArrayList<Characters>>()
    val characters:LiveData<ArrayList<Characters>> = _characters


    private val _charactersInHouse = MutableLiveData<ArrayList<Characters>>()
    val charactersInHouse : LiveData<ArrayList<Characters>> =  _charactersInHouse

     val isLoading = MutableLiveData<Boolean>()

    private fun fetchAllCharacters(){
        viewModelScope.launch {
            isLoading.value = true
            val response = PotterApi.retrofitService.getAllCharacters()
            if (response.isSuccessful){
                _characters.value = response.body()
                _charactersInHouse.value = response.body()
                isLoading.value = false


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