package com.sina.navigationkotlin.models

import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import kotlinx.android.parcel.Parcelize;

@Parcelize
data class MovieCast(
    @SerializedName("id")
    val id : String?,

    @SerializedName("name")
    val name : String?,

    @SerializedName("character")
    val character : String?,

    @SerializedName("profile_path")
    val profile : String?


) : Parcelable {
    constructor() : this("","",""," ")
}
