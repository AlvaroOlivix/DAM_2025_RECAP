package com.dam.dam_2025_recap.data

import com.dam.dam_2025_recap.feature.movie.data.MovieDataRepository
import com.dam.dam_2025_recap.feature.movie.data.local.LocalXmlDataSource
import com.dam.dam_2025_recap.feature.movie.data.remote.MockRemoteDataSource
import com.dam.dam_2025_recap.feature.movie.domain.Movie
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class LocalDataTest {

    @Mock
    private lateinit var mockRemoteDataSource: MockRemoteDataSource

    @Mock
    private lateinit var localXmlDataSource: LocalXmlDataSource
    private lateinit var movieDataRepository: MovieDataRepository

    @Before
    fun setUp() {
        movieDataRepository = MovieDataRepository(localXmlDataSource, mockRemoteDataSource)
    }

    @Test
    fun getMovieListTestReturnListOfMovies() = runBlocking {
        val localMovies = listOf(
            Movie(
                "1",
                "PeliculaTest",
                "DescripciónTest",
                "imageUrl"
            )
        )

        Mockito.`when`(localXmlDataSource.findMovies()).thenReturn(localMovies)
        val result = movieDataRepository.getMovies()
        assertEquals(result, localMovies)
    }

    @Test
    fun getMovieListTestWhenLocalFailsReturnsMoviesFromRemote() = runBlocking {

        val remoteMovies = listOf(
            Movie(
                "1",
                "PeliculaTest",
                "DescripciónTest",
                "imageUrl"
            )
        )

        Mockito.`when`(localXmlDataSource.findMovies()).thenReturn(emptyList())

        // Configuramos el mock para simular que la fuente remota devuelve datos
        Mockito.`when`(mockRemoteDataSource.getMovieList()).thenReturn(remoteMovies)

        val result = movieDataRepository.getMovies()
        assertEquals(result, remoteMovies)
    }

    @Test
    fun getMovieByIDReturnsMovieFromLocal() = runBlocking {
        val localMovie = Movie(
            "1",
            "PeliculaTest",
            "DescripciónTest", "u"
        )
        whenever(localXmlDataSource.findMovieById("1")).thenReturn(localMovie)
        val result = movieDataRepository.getMovie("1")
        assertEquals(result, localMovie)
    }

    @Test
    fun getMovieByIdWhenLocalDataIsNull () = runBlocking {
        val remoteMovie = Movie(
            "1",
            "PeliculaTest",
            "DescripciónTest", "u"
        )
        whenever(localXmlDataSource.findMovieById("1")).thenReturn(null)
        whenever(mockRemoteDataSource.getMovieById("1")).thenReturn(remoteMovie)
        val result = movieDataRepository.getMovie("1")
        assertEquals(result, remoteMovie)

    }
}