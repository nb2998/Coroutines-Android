package com.apps.nb2998.coroutineswithandroid

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {
    val BASE_URl = "https://api.chucknorris.io"

    fun makeChuckNorrisApi(): ChuckNorrisApi =
        Retrofit.Builder()
            .baseUrl(BASE_URl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(ChuckNorrisApi::class.java)
}