package com.hikizan.movietvapp.data.movietv.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hikizan.movietvapp.data.movietv.local.model.entity.MovieItemEntity
import com.hikizan.movietvapp.data.movietv.local.model.entity.TvShowItemEntity

@Database(
    entities = [MovieItemEntity::class, TvShowItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieTvDatabase : RoomDatabase() {
    abstract fun movieTvDao(): MovieTvDao
}