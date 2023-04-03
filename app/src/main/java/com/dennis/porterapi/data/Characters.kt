package com.dennis.porterapi.data

import com.google.gson.annotations.SerializedName

data class Characters(
    val id: String,
    val name:String,
    @SerializedName("alternate_names") val alias:List<String>,
    val species:String,
    val gender:String,
    val house:String,
    @SerializedName("dateOfBirth")val dateOfBirth:String?,
    val ancestry:String,
    val hairColour:String,
    val eyeColour:String,
    val wand:Wand,
    val patronus:String?,
    val actor:String,
    val image:String
)
