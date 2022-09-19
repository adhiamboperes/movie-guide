package com.adhiambo.movieguide.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.adhiambo.movieguide.common.MovieGuideApp
import com.adhiambo.movieguide.config.MovieGuideException
import com.adhiambo.movieguide.data.Movie
import io.reactivex.Single
import javax.inject.Inject


class MovieNetworkSource @Inject constructor(private val restApi: RestApi) {

    fun getMovies(): Single<List<Movie>> {
        return Single.fromCallable {
            if (!isNetworkAvailable(MovieGuideApp.instance)) {
                throw  MovieGuideException(msg = "A network error occurred")
            } else {
                val response = restApi.getMovies().execute()
                if (!response.isSuccessful) {
                    throw MovieGuideException()
                }
                response.body()?.results
            }
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }
}