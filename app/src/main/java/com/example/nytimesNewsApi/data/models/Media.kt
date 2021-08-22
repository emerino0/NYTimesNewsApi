package com.example.nytimesNewsApi.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Media(
    @SerializedName("type")
    var type: String,
    @SerializedName("subtype")
    var subtype: String,
    @SerializedName("caption")
    var caption: String,
    @SerializedName("copyright")
    var copyright: String,
    @SerializedName("approved_for_syndication")
    var approvedForSyndication: Int,
    @SerializedName("media-metadata")
    var mediaMetaData: List<MediaMetadata>
): Parcelable