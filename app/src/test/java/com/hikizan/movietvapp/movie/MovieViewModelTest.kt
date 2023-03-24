package com.hikizan.movietvapp.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.hikizan.movietvapp.core.data.movietv.Resource
import com.hikizan.movietvapp.core.domain.movietv.MovieTvUseCase
import com.hikizan.movietvapp.core.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.viewmodel.MovieViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class MovieViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get movies from api`() {
        val movieUseCase = Mockito.mock(MovieTvUseCase::class.java)
        val listMovieFlow =  flowOf(Resource.Success(dummyMovies))

        Mockito.`when`(movieUseCase.getMovies())
            .thenReturn(listMovieFlow)
        val movieViewModel = MovieViewModel(movieUseCase)
        val result = movieViewModel.getMovies()

        Mockito.verify(movieUseCase, Mockito.atLeastOnce()).getMovies()
        Mockito.verifyNoMoreInteractions(movieUseCase)
        Assert.assertNotNull(result)
    }

    private val dummyMovies = listOf<MovieItem>(
        MovieItem(
            id = 1077280,
            backdropPath = "/pxJbfnMIQQxCrdeLD0zQnWr6ouL.jpg",
            originalTitle = "Die Hart",
            overview = "Kevin Hart - playing a version of himself - is on a death-defying quest to become an action star. And with a little help from John Travolta, Nathalie Emmanuel, and Josh Hartnett - he just might pull it off.",
            releaseDate = "2023-02-22",
            popularity = 852.881,
            voteAverage = 6.1,
            title = "Die Hart",
            voteCount = 208,
            posterPath = "/1EnBjTJ5utgT1OXYBZ8YwByRCzP.jpg",
            isFavorite = false
        ),
        MovieItem(
            id = 315162,
            backdropPath = "/jr8tSoJGj33XLgFBy6lmZhpGQNu.jpg",
            originalTitle = "Puss in Boots: The Last Wish",
            overview = "Puss in Boots discovers that his passion for adventure has taken its toll: He has burned through eight of his nine lives, leaving him with only one life left. Puss sets out on an epic journey to find the mythical Last Wish and restore his nine lives.",
            releaseDate = "2022-12-07",
            popularity = 1741.231,
            voteAverage = 8.3,
            title = "Puss in Boots: The Last Wish",
            voteCount = 4790,
            posterPath = "/kuf6dutpsT0vSVehic3EZIqkOBt.jpg",
            isFavorite = true
        )
    )
}