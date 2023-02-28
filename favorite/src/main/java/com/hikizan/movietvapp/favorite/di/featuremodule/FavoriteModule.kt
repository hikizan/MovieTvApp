package com.hikizan.movietvapp.favorite.di.featuremodule

import com.hikizan.movietvapp.favorite.viewmodel.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}