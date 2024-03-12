package com.example.assessment.client

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


public interface TmdbApiClient {
    @GET("discover/movie?api_key=9734662f924142c9ac1c0fc85a15ad3a&language=pt-BR")
    fun listMoviesByGenre(@Query("with_genres") genre: String?): Call<TmdbDiscoverResult?>?

    @GET("discover/movie?&api_key=9734662f924142c9ac1c0fc85a15ad3a&language=pt-BR")
    fun listMovies(): Call<TmdbDiscoverResult?>?

    @GET("movie/{movieId}?&append_to_response=credits&api_key=9734662f924142c9ac1c0fc85a15ad3a&language=pt-BR")
    fun getMovieDetail(@Path("movieId") movieId: Int): Call<TmdbMovieDetail?>?

}