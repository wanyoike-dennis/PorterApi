package com.dennis.porterapi.viewmodel


import android.util.Log
import androidx.lifecycle.*
import com.dennis.porterapi.data.Characters
import com.dennis.porterapi.repository.CharactersRepository
import kotlinx.coroutines.launch


class CharactersViewModel(private val charactersRepository :CharactersRepository) : ViewModel() {


    private val _characters = MutableLiveData<List<Characters>>()
    val characters: LiveData<List<Characters>> = _characters


    private val _charactersInHouse = MutableLiveData<ArrayList<Characters>>()
    val charactersInHouse: LiveData<ArrayList<Characters>> = _charactersInHouse

    val isLoading = MutableLiveData<Boolean>()



    /**
     * Gets  information from the Potter API Retrofit service and updates the
     * [Characters] [List] [LiveData].
     */
    private fun getAllCharacters() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val characters = charactersRepository.fetchAllCharacters()
                _characters.postValue(characters)
                _characters.postValue(characters)
                isLoading.value= false
            } catch (e:Exception){
                Log.e(TAG,"could not load characters",e)
            }


        }


    }
    companion object{
        private const val TAG = "viewModel"
    }

    /**
     * Call getAllCharacters() on init so we can display status immediately.
     */
    init {
        getAllCharacters()
    }

}
class CharactersModelFactory(private val charactersRepository :CharactersRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CharactersViewModel(charactersRepository) as T
        }
        throw IllegalArgumentException("unknown viewModel class")
    }
}