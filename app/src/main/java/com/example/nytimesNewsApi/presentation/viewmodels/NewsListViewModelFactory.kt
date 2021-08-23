package com.example.nytimesNewsApi.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nytimesNewsApi.data.repositories.NewsRepository

class NewsListViewModelFactory(
    val app: Application,
    private val newsRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsListViewModel(app, newsRepository) as T
    }

}