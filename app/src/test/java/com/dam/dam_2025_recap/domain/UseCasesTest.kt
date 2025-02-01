package com.dam.dam_2025_recap.domain

import com.dam.dam_2025_recap.feature.movie.domain.GetMovieListUseCase
import com.dam.dam_2025_recap.feature.movie.domain.GetMovieUseCase
import com.dam.dam_2025_recap.feature.movie.domain.Movie
import com.dam.dam_2025_recap.feature.movie.domain.MovieRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class UseCasesTest {

    @Mock
    private lateinit var movieRepository: MovieRepository
    private lateinit var getMovieUseCase: GetMovieUseCase
    private lateinit var getMovieListUseCase: GetMovieListUseCase

    @Before
    fun setup() {
        getMovieUseCase = GetMovieUseCase(movieRepository)
        getMovieListUseCase = GetMovieListUseCase(movieRepository)
    }

    @Test
    fun getMovieUseCaseTestReturnMovie() = runBlocking {
        val movie = Movie(
            "1",
            "PeliculaTest",
            "DescripciónTest",
            "imageUrl"
        )

        whenever(movieRepository.getMovie("1")).thenReturn(movie)
        val result = getMovieUseCase.invoke("1")
        assertEquals("Pelicula", result, movie)
    }

    @Test
    fun getMovieListTestReturnListOfMovies() = runBlocking {
        val movies = listOf(
            Movie(
                "1",
                "PeliculaTest",
                "DescripciónTest",
                "imageUrl"
            ),
            Movie(
                "2",
                "PeliculaTest2",
                "DescripciónTest2",
                "imageUrl2"
            )
        )
        whenever(movieRepository.getMovies()).thenReturn(movies)
        val result = getMovieListUseCase()
        assertEquals(result, movies)
    }
}