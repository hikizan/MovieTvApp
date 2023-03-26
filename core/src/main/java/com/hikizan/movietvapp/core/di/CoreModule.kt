package com.hikizan.movietvapp.core.di

import androidx.room.Room
import com.hikizan.movietvapp.core.BuildConfig
import com.hikizan.movietvapp.core.data.movietv.MovieTvRepository
import com.hikizan.movietvapp.core.data.movietv.local.LocalDataSource
import com.hikizan.movietvapp.core.data.movietv.local.room.MovieTvDatabase
import com.hikizan.movietvapp.core.data.movietv.remote.RemoteDataSource
import com.hikizan.movietvapp.core.data.movietv.remote.network.MovieTvApiClient
import com.hikizan.movietvapp.core.domain.movietv.repositoryimpl.MovieTvRepositoryImpl
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("hikizan".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieTvDatabase::class.java, "MovieTvDB"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/NPIMWkzcNG/MyZsVExrC6tdy5LTZzeeKg2UlnGG55UY=")
            .add(hostname, "sha256/DxH4tt40L+eduF6szpY6TONlxhZhBd+pJ9wbHlQ2fuw=")
            .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, SECONDS)
            .readTimeout(120, SECONDS)
            .certificatePinner(certificatePinner)
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
    single<MovieTvRepositoryImpl> { MovieTvRepository(get(), get()) }
}