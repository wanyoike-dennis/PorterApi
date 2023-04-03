package com.dennis.porterapi

import com.dennis.porterapi.network.PotterApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Test


class GetAllCharactersTest {

    private val apiService = PotterApi.retrofitService

    @Test
    fun  getCharacters(){
        runBlocking {

           // Make the API call to get the list of characters
            val response = apiService.getAllCharacters()

            // Check that the response is successful
            assertThat(response.isSuccessful).isTrue()

            //Check that the response body is not null
            val characters = response.body()
            assertThat(characters).isNotNull()

            // check that the list of characters is not empty
            assertThat(characters?.isNotEmpty()).isTrue()




        }
    }



}