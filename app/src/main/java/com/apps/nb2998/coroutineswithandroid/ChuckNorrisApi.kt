package com.apps.nb2998.coroutineswithandroid

import retrofit2.Call
import retrofit2.http.GET

interface ChuckNorrisApi {

    @GET("jokes/random")
    suspend fun fetchJoke(): JokeResponse
}