package com.adhiambo.movieguide.common.di

import android.app.Application
import com.adhiambo.movieguide.common.MovieGuideApp
import com.adhiambo.movieguide.common.di.modules.ActivityModule
import com.adhiambo.movieguide.common.di.modules.AppModule
import com.adhiambo.movieguide.common.di.modules.FragmentModule
import com.adhiambo.movieguide.common.di.modules.ViewModelModule
import com.adhiambo.movieguide.data.network.MovieNetworkSource
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
        AndroidInjectionModule::class
    ]
)

interface AppComponent : AndroidInjector<MovieGuideApp> {
    fun inject(networkSource: MovieNetworkSource)

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): AppComponent
    }
}
