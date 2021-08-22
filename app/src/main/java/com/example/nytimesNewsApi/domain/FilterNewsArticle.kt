package com.example.nytimesNewsApi.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FilterNewsArticle (
    var articleType: String,
    var period: String,
    var sharedType: String
): Parcelable