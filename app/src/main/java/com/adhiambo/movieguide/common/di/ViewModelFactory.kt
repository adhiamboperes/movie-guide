package com.adhiambo.movieguide.common.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adhiambo.movieguide.OpenForTesting
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
@OpenForTesting
class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModels[modelClass]?.get() as T

}
