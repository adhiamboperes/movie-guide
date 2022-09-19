package com.adhiambo.movieguide.common.di.modules

import android.app.Application
import android.content.Context
import com.adhiambo.movieguide.OpenForTesting
import com.adhiambo.movieguide.common.DebugErrorLogger
import com.adhiambo.movieguide.common.ErrorLogger
import com.adhiambo.movieguide.config.RestClient
import com.adhiambo.movieguide.data.network.MovieNetworkSource
import com.adhiambo.movieguide.data.network.RestApi
import com.adhiambo.movieguide.data.repository.MovieRepository
import com.adhiambo.movieguide.data.usecase.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@OpenForTesting
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideRestApi(): RestApi = RestClient.getClient()

    @Provides
    @Singleton
    fun provideMovieNetworkSource(restApi: RestApi): MovieNetworkSource {
        return MovieNetworkSource(restApi)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(networkSource: MovieNetworkSource): MovieRepository {
        return MovieRepository(networkSource)
    }

    @Provides
    @Singleton
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMoviesUseCase {
        return GetMoviesUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideErrorLogger(): ErrorLogger {
        return DebugErrorLogger()
    }
}