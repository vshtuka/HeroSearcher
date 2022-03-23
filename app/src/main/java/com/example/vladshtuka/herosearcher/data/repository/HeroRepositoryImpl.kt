package com.example.vladshtuka.herosearcher.data.repository

import androidx.lifecycle.LiveData
import com.example.vladshtuka.herosearcher.domain.model.Hero
import com.example.vladshtuka.herosearcher.data.retrofit.HeroApiService
import com.example.vladshtuka.herosearcher.data.room.HeroDao
import com.example.vladshtuka.herosearcher.domain.repository.HeroRepository

class HeroRepositoryImpl (
    private val retrofitService: HeroApiService,
    private val heroDao: HeroDao
) :
    HeroRepository {

    override suspend fun getHeroesFromApi(): List<Hero> {
        return retrofitService.getAllHeroes()
    }

    override suspend fun getAllHeroes(): List<Hero> {
        return heroDao.getAllHeroes()
    }

    override suspend fun insertHero(hero: Hero?) {
        heroDao.insertHero(hero!!)
    }

    override suspend fun updateHero(isLiked: Boolean, name: String) {
        heroDao.update(isLiked, name)
    }

    override suspend fun getHeroName(name: String): String? {
        return heroDao.getHeroName(name)
    }

    override fun getFavoriteHeroes(): LiveData<List<Hero>> {
        return heroDao.getFavoriteHeroes()
    }


}