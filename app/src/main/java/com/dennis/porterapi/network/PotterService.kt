package com.dennis.porterapi.network

import com.dennis.porterapi.commons.BASE_URL
import com.dennis.porterapi.data.Characters
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface PotterService{
    @GET("characters")
    suspend fun getAllCharacters():Response<ArrayList<Characters>>

}

object PotterApi{
    val retrofitService : PotterService by lazy {
        retrofit.create(PotterService::class.java)
    }
}