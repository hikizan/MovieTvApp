package com.hikizan.movietvapp.di.featureModule

import com.hikizan.movietvapp.core.domain.movietv.MovieTvInteractor
import com.hikizan.movietvapp.core.domain.movietv.MovieTvUseCase
import com.hikizan.movietvapp.viewmodel.MovieViewModel
import com.hikizan.movietvapp.viewmodel.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieTvUseCase> { MovieTvInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
}