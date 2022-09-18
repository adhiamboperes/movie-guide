package com.adhiambo.movieguide.common.di

import android.app.Application
import com.adhiambo.movieguide.common.MovieGuideApp
import com.adhiambo.movieguide.common.di.modules.ActivityModule
import com.adhiambo.movieguide.common.di.modules.AppModule
import com.adhiambo.movieguide.common.di.modules.FragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityModule::class,
        AppModule::class,
        FragmentModule::class,
        AndroidInjectionModule::class
    ]
)

interface AppComponent : AndroidInjector<MovieGuideApp> {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance application: Application): AppComponent
    }
}
