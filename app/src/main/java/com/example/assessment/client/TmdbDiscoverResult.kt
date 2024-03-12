package com.example.assessment.client

data class TmdbDiscoverResult(
    val page: Int,
    val results: List<TmdbMovie>,
    val total_pages: Int,
    val total_results: Int
)