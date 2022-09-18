package com.adhiambo.movieguide.common.di.modules

import android.app.Application
import android.content.Context
import com.adhiambo.movieguide.OpenForTesting
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
}