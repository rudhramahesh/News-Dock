package com.rudhra.newsdoc.ui.model

import com.squareup.moshi.Json

data class Source(
    @Json(name ="id")
    var id: String? = null,
    @Json(name ="name")
    var name: String? = null
)