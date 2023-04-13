package com.dennis.porterapi.repository

import com.dennis.porterapi.data.Characters
import com.dennis.porterapi.network.PotterApi
import retrofit2.HttpException

class CharactersRepository(private val apiService:PotterApi) {

    suspend fun fetchAllCharacters():List<Characters>{
    val response = apiService.retrofitService.getAllCharacters()
        if (response.isSuccessful){
            return response.body() ?: emptyList()
        }
        else {
            throw  HttpException(response)
        }
    }


}