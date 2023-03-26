package com.hikizan.movietvapp.core.domain.movietv

import com.hikizan.movietvapp.core.data.movietv.Resource
import com.hikizan.movietvapp.core.domain.movietv.model.response.MovieItem
import com.hikizan.movietvapp.core.domain.movietv.repositoryimpl.MovieTvRepositoryImpl
import kotlinx.coroutines.flow.flowOf
import org.junit.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

@RunWith(MockitoJUnitRunner::class)
class MovieTvUseCaseTest {

    private lateinit var movieTvUseCase: MovieTvUseCase

    @Mock private lateinit var movieTvRepository: MovieTvRepositoryImpl

    private val dummyMovies = listOf(
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

    @Before
    fun setup() {
        movieTvUseCase = MovieTvInteractor(movieTvRepository)
        val moviesFlow = flowOf(Resource.Success(dummyMovies))

        `when`(movieTvRepository.getMovies()).thenReturn(moviesFlow)
    }

    @Test fun `should get movies from repository`() {
        val movies = movieTvUseCase.getMovies()

        verify(movieTvRepository).getMovies()
        verifyNoMoreInteractions(movieTvRepository)
        Assert.assertNotNull(movies)
    }
}