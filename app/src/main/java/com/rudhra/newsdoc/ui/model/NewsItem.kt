package com.rudhra.newsdoc.ui.model

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rudhra.newsdoc.R
import com.rudhra.newsdoc.ui.model.NewsItem.Companion.DATE_FORMAT
import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

//extension function to find the difference of time from current time in hours
fun String.formatDate(): Long {
    val simpleDateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    val date: Date? = simpleDateFormat.parse(this)
    return TimeUnit.MILLISECONDS.toHours(Date().time - date?.time!!)
}

data class NewsItem(
    @Json(name = "source")
    var source: Source? = null,

    @Json(name = "author")
    var author: String? = null,

    @Json(name = "title")
    var title: String? = null,

    @Json(name = "description")
    var description: String? = null,

    @Json(name = "url")
    var url: String? = null,

    @Json(name = "urlToImage")
    var urlToImage: String? = null,

    @Json(name = "publishedAt")
    var publishedAt: String? = null,

    @Json(name = "content")
    var content: String? = null,

    var hoursAgo: Long? = null
) {


    companion object {

        const val DATE_FORMAT: String = "yyyy-MM-dd'T'HH:mm:SS'Z'"

        // static method for image loading
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun bindImage(imgView: ImageView, imgUrl: String?) {
            imgUrl?.let {
                val imgUri =
                    imgUrl.toUri().buildUpon().scheme("https").build()
                Glide.with(imgView.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgView)
            }
        }
    }
}