package com.hikizan.movietvapp.di

import androidx.room.Room
import com.hikizan.movietvapp.core.BuildConfig
import com.hikizan.movietvapp.core.data.movietv.MovieTvRepository
import com.hikizan.movietvapp.core.data.movietv.local.LocalDataSource
import com.hikizan.movietvapp.core.data.movietv.local.room.MovieTvDatabase
import com.hikizan.movietvapp.core.data.movietv.remote.RemoteDataSource
import com.hikizan.movietvapp.core.data.movietv.remote.network.MovieTvApiClient
import com.hikizan.movietvapp.core.domain.movietv.repositoryimpl.MovieTvRepositoryImpl
import com.hikizan.movietvapp.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

val databaseModule = module {
    factory { get<MovieTvDatabase>().movieTvDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieTvDatabase::class.java, "MovieTv.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, SECONDS)
            .readTimeout(120, SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(MovieTvApiClient::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<MovieTvRepositoryImpl> { MovieTvRepository(get(), get(), get()) }
}