package com.example.nytimesNewsApi.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.nytimesNewsApi.data.repositories.NewsRepository

class NewsListViewModel(
    app: Application,
    private val newsRepository: NewsRepository
) : AndroidViewModel(app){

    fun newsResult() =
        newsRepository.getNewsResponseLiveData()
}