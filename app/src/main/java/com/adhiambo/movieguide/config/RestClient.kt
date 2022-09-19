package com.adhiambo.movieguide.config

import com.adhiambo.movieguide.data.network.RestApi
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    fun getClient(): RestApi {
        val gson = GsonBuilder().create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(NetworkClient.client)
            .build()
            .create(RestApi::class.java)
    }
}