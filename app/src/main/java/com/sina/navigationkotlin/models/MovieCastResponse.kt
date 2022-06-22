package com.sina.navigationkotlin.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieCastResponse(
    @SerializedName("cast")
    val movieCasts: List<MovieCast>
) : Parcelable{
    constructor() : this(mutableListOf()) }
