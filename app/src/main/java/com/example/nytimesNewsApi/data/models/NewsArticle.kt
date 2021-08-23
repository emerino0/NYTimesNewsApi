package com.example.nytimesNewsApi.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsArticle(
    @SerializedName("id")
    var id: Long,
    @SerializedName("title")
    var title: String,
    @SerializedName("byline")
    var byline: String,
    @SerializedName("section")
    var section: String,
    @SerializedName("published_date")
    var publishedDate: String,
    @SerializedName("url")
    var url: String,
    @SerializedName("media")
    var media: List<Media>
): Parcelable