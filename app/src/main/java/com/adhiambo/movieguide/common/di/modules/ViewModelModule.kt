package com.adhiambo.movieguide.common.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adhiambo.movieguide.common.di.ViewModelFactory
import com.adhiambo.movieguide.common.di.ViewModelKey
import com.adhiambo.movieguide.presentation.MoviesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
   internal abstract fun provideMoviesViewModel(moviesViewModel: MoviesViewModel): ViewModel
}