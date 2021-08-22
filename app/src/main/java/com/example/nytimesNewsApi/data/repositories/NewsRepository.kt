package com.example.nytimesNewsApi.data.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nytimesNewsApi.data.models.NewsArticleResponse
import com.example.nytimesNewsApi.data.remote.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(articleType: String, period: String,shareType: String) {

    private val newsService = RetrofitInstance.api
    private val newsResponseLiveData: MutableLiveData<NewsArticleResponse> = MutableLiveData()

    init {
        getNewsResponse(articleType, period, shareType)
    }

    private fun getNewsResponse(articleType: String, period: String,shareType: String) {

        val retrofitResponse = object : Callback<NewsArticleResponse> {
            override fun onResponse(
                call: Call<NewsArticleResponse>,
                response: Response<NewsArticleResponse>
            ) {

                if (response.body() != null) {
                    newsResponseLiveData.postValue(response.body())

                }
            }

            override fun onFailure(call: Call<NewsArticleResponse>, t: Throwable) {
                newsResponseLiveData.postValue(null)
                Log.d("error ibm", t.message.toString())
            }
        }

        if(articleType == "shared")
            newsService.getNewsShared(articleType,period,shareType).enqueue(retrofitResponse)
        else
            newsService.getNews(articleType,period).enqueue(retrofitResponse)
    }

    fun getNewsResponseLiveData(): MutableLiveData<NewsArticleResponse> {
        return newsResponseLiveData
    }
}