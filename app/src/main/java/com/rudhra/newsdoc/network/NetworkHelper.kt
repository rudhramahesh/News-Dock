package com.rudhra.newsdoc.network

import com.rudhra.newsdoc.BuildConfig
import com.rudhra.newsdoc.ui.model.Articles
import com.rudhra.newsdoc.ui.model.NewsResult

object NetworkHelper {

    fun getNewsList(country: String, category: String): NewsResult<Articles> {

        val request = RetrofitBuilder.buildService(NetworkInterface::class.java)
        return try {
            val call = request.getNewsArticles(country, category,BuildConfig.API_KEY)
            NewsResult.Success(call.execute().body()!!)
        } catch (e: Exception) {
            NewsResult.Failure(e.message)
        }
    }
}