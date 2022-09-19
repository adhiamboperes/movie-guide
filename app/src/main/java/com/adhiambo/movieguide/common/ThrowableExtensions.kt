package com.adhiambo.movieguide.common

fun Throwable.orCause(): Throwable {
    return cause?.orCause() ?: this
}
