package com.example.vladshtuka.herosearcher.data.retrofit

import com.example.vladshtuka.herosearcher.domain.model.Hero
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface HeroApiService {
    @GET("characters")
    suspend fun getAllHeroes(): List<Hero>

    companion object {
        const val BASE_URL =
            "https://breakingbadapi.com/api/"
    }
}
