package com.adhiambo.movieguide.common.di.modules

import com.adhiambo.movieguide.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun provideMainActivity(): MainActivity
}