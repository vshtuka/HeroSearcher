package com.example.vladshtuka.herosearcher.domain.repository

import androidx.lifecycle.LiveData
import com.example.vladshtuka.herosearcher.domain.model.Hero

interface HeroRepository {

    suspend fun getHeroesFromApi(): List<Hero>

    suspend fun getAllHeroes(): List<Hero>

    suspend fun insertHero(hero: Hero?)

    suspend fun updateHero(isLiked: Boolean, name: String)

    suspend fun getHeroName(name: String): String?

    fun getFavoriteHeroes(): LiveData<List<Hero>>

}