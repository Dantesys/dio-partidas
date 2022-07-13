package com.dantesys.dio_aula.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    @SerializedName("nome")
    val nome:String,
    @SerializedName("forca")
    val force:Int,
    @SerializedName("img")
    val img:String,
    var score:Int?
): Parcelable