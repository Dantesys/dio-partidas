package com.dantesys.dio_aula.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Match(
    @SerializedName("local")
    val place:Place,
    @SerializedName("casa")
    val homeTeam:Team,
    @SerializedName("fora")
    val awayTeam:Team,
    @SerializedName("desc")
    val description:String
): Parcelable