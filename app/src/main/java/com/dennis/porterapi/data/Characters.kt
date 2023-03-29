package com.dennis.porterapi.data

import com.google.gson.annotations.SerializedName

data class Characters(
    val id: String,
    val name:String,
    val species:String,
    val gender:String,
    val house:String,
    @SerializedName("dateOfBirth")val dateOfBirth:String?,
   @SerializedName("image") val image:String
)
