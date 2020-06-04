package com.rudhra.newsdoc.ui.model


sealed class NewsResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : NewsResult<T>()

    data class Failure(val error: String?) : NewsResult<Nothing>()

}

