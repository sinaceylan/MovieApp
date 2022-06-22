package com.sina.navigationkotlin.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CastResponse(
    @SerializedName("cast")
    val casts: List<Cast>
) : Parcelable{
    constructor() : this(mutableListOf())

}
