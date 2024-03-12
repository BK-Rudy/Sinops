package com.example.assessment.services

import com.example.assessment.*
import com.example.assessment.client.TmdbApiClient
import com.example.assessment.client.TmdbDiscoverResult
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import com.example.assessment.client.TmdbMovieDetail
import retrofit2.Call
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MovieService {

    private lateinit var apiClient: TmdbApiClient

    var movies: MutableList<Movie> = mutableListOf()

    init {

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiClient = retrofit.create(TmdbApiClient::class.java)

    }

    fun getMoviesByGenre(genre: String): MutableList<Movie> {

        val map = hashMapOf(
            "Ação" to "28",
            "Aventura" to "12",
            "Comédia" to "35",
            "Drama" to "18"
        )
        var id = map[genre]
        var callAsync = apiClient.listMoviesByGenre(id)
        movies = LoadMovies(callAsync)

        return movies.take(5).toMutableList()

    }

    private fun LoadMovies(callAsync: Call<TmdbDiscoverResult?>?): MutableList<Movie> {
        val response: Response<TmdbDiscoverResult?>? = callAsync?.execute()
        val apiResponse: TmdbDiscoverResult? = response?.body()

        movies =
            (apiResponse?.results?.take(5)?.map { m ->
                Movie(
                    m.id,
                    "https://image.tmdb.org/t/p/w92/${m.poster_path}",
                    m.title,
                    "",
                    "${m.vote_average.toString()} / 10",
                    DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.parse(m.release_date)),
                    m.overview,
                    "https://image.tmdb.org/t/p/w300/${m.backdrop_path}"
                )
            })?.toMutableList()!!

        return movies;
    }

    fun getAllMovies(): MutableList<Movie> {

        movies = LoadMovies(apiClient.listMovies()).take(5).toMutableList()
        return movies;
    }

    fun getMovieById(id: Int) : TmdbMovieDetail? {
        var callAsync = apiClient.getMovieDetail(id)
        val response: Response<TmdbMovieDetail?>? = callAsync?.execute()
        val apiResponse: TmdbMovieDetail? = response?.body()

        return apiResponse
    }


}