package com.adhiambo.movieguide.config

import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


object NetworkClient {
    private val dispatcher = Dispatcher()
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client: OkHttpClient
        get() {
            dispatcher.maxRequests = 2

            val builder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val original = chain.request()

                    val request = original.newBuilder().apply {
                        addHeader("Accept", "application/json; version=1")
                        addHeader("App", "MovieGuide")
                    }
                        .build()

                    chain.proceed(request)
                }
                .dispatcher(dispatcher)
                .cache(null)

            builder.addInterceptor(loggingInterceptor)

            return builder.build()
        }
}