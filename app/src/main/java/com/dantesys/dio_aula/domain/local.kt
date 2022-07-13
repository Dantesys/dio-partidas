package com.dantesys.dio_aula.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    @SerializedName("nome")
    val name:String,
    @SerializedName("img")
    val img:String
):Parcelable