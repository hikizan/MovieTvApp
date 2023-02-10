package com.hikizan.movietvapp.data.movietv.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hikizan.movietvapp.data.movietv.local.model.entity.MovieItemEntity
import com.hikizan.movietvapp.data.movietv.local.model.entity.TvShowItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieTvDao {
    @Query("SELECT * FROM movieItem")
    fun getMovies(): Flow<List<MovieItemEntity>>

    @Query("SELECT * FROM tvShowItem")
    fun getTvShows(): Flow<List<TvShowItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = MovieItemEntity::class)
    suspend fun insertMovies(movies: List<MovieItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TvShowItemEntity::class)
    suspend fun insertTvShows(tvShows: List<TvShowItemEntity>)

    @Update
    fun updateFavoriteMovie(movie: MovieItemEntity)

    @Update
    fun updateFavoriteTvShow(tvShow: TvShowItemEntity)
}