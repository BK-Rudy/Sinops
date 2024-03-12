package com.example.assessment

import com.example.assessment.services.MovieService
import org.junit.Test

import org.junit.Assert.*

/**
 * Unit tests covering MovieService functionalities
 */
class MovieServiceTest {
    @Test
    fun movieService_getAllMovies_Successfully() {
        var sut = MovieService()

        sut.movies = getMoviesStub()

        var expectedMovieCount = 2
        var actualMovieCount = sut.getAllMovies().count()

        assertEquals(expectedMovieCount, actualMovieCount)

    }

    @Test
    fun movieService_getMoviesByGenre_Successfully() {
        var sut = MovieService()

        sut.movies = getMoviesStub()

        var expectedMovieTitle = "Test Movie #1"
        var actualMovieTitle = sut.getMoviesByGenre("Drama").first().title

        assertEquals(expectedMovieTitle, actualMovieTitle)

    }

    private fun getMoviesStub() = mutableListOf(
        Movie(
            1, "", "Test Movie #1", "", "", "",
            "", ""
        ),
        Movie(
            1, "","Test Movie #2", "", "", "",
            "", ""
        )
    )
}