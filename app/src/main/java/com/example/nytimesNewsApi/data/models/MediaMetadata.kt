package com.example.nytimesNewsApi.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaMetadata(
    @SerializedName("url")
    var url: String,
    @SerializedName("format")
    var format: String,
    @SerializedName("height")
    var height: Int,
    @SerializedName("width")
    var width: Int
): Parcelable