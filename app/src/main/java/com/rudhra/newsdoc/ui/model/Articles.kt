package com.rudhra.newsdoc.ui.model

import com.squareup.moshi.Json

data class Articles(
    @Json(name = "status")
    var status: String? = null,

    @Json(name ="totalResults")
    var totalResults: Int? = null,

    @Json(name ="articles")
    var newsItems: List<NewsItem>? = null
)