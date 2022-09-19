package com.adhiambo.movieguide.common

import android.app.Application
import androidx.annotation.VisibleForTesting
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import com.adhiambo.movieguide.common.di.DaggerAppComponent
import com.adhiambo.movieguide.data.MoviesDatabase
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class MovieGuideApp : Application(), HasAndroidInjector, ImageLoaderFactory {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var errorLogger: ErrorLogger

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        database = MoviesDatabase.invoke(this)
        setUpDagger()
        RxJavaPlugins.setErrorHandler { e ->
            var error = e
            // RxJava wraps such errors with the UndeliverableException
            // but we want to process the wrapped exception
            if (e is UndeliverableException) {
                error = e.orCause()
            }

            errorLogger.logException(
                throwable = error,
                message = "Exception handled by RxJavaPlugins.setErrorHandler",
                severity = ErrorLogger.Severity.WARNING
            )
        }
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    open fun setUpDagger() {
        DaggerAppComponent
            .factory()
            .create(this)
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    companion object {
        lateinit var instance: MovieGuideApp
        lateinit var database: MoviesDatabase
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .diskCache {
                DiskCache.Builder()
                    .directory(this.cacheDir.resolve("movies_image_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }
            .build()
    }
}