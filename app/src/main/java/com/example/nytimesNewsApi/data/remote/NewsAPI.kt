package com.example.nytimesNewsApi.data.remote

import com.example.nytimesNewsApi.data.models.NewsArticleResponse
import com.example.nytimesNewsApi.data.util.Constants.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsAPI {

    //MostViewed and MostEmailed
    @GET("{articleType}/{period}.json?api-key=${API_KEY}")
    fun getNews(@Path("articleType")articleType: String,
                @Path("period")period: String): Call<NewsArticleResponse>

    //MostShared
    @GET("{articleType}/{shareType}/{period}.json?api-key=${API_KEY}")
    fun getNewsShared(@Path("articleType")articleType: String,
                      @Path("shareType")shareType: String,
                      @Path("period")period: String)
            : Call<NewsArticleResponse>

}