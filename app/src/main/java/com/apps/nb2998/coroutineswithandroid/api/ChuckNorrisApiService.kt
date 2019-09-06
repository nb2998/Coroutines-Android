package com.apps.nb2998.coroutineswithandroid.api

import com.apps.nb2998.coroutineswithandroid.JokeResponse
import retrofit2.http.GET

interface ChuckNorrisApiService {

    @GET("jokes/random")
    suspend fun fetchJoke(): JokeResponse
}