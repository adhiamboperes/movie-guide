package com.adhiambo.movieguide.config

open class MovieGuideException(
    private val msg: String = "Unknown error",
    val throwable: Throwable? = null
) : Exception(msg, throwable)