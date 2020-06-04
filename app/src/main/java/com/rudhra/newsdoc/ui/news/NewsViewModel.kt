package com.rudhra.newsdoc.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rudhra.newsdoc.BuildConfig
import com.rudhra.newsdoc.network.NetworkHelper
import com.rudhra.newsdoc.ui.model.Articles
import com.rudhra.newsdoc.ui.model.NewsItem
import com.rudhra.newsdoc.ui.model.NewsResult
import com.rudhra.newsdoc.ui.model.formatDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsViewModel(private val category: String) : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(
        viewModelJob + Dispatchers.IO
    )
    private val _newsList = MutableLiveData<List<NewsItem>>()
    val newsList: LiveData<List<NewsItem>> = _newsList

    init {
        getNewsData(category)
    }

    private fun getNewsData(category: String) {
        coroutineScope.launch(Dispatchers.IO) {

            when (val newsResult: NewsResult<Articles> =
                NetworkHelper.getNewsList(BuildConfig.COUNTRY, category)) {
                is NewsResult.Success -> {
                    //find the difference of time from current time in hours
                    newsResult.data.newsItems?.map {
                        it.hoursAgo = it.publishedAt?.formatDate()
                        it.publishedAt = "${it.hoursAgo} hours ago"
                    }
                    //sort the list by latest time
                    newsResult.data.newsItems?.sortedBy { it.hoursAgo }
                    _newsList.postValue(newsResult.data.newsItems)
                }
                is NewsResult.Failure -> _newsList.postValue(listOf())
            }
        }
    }

    fun onRefresh() {
        getNewsData(category)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}