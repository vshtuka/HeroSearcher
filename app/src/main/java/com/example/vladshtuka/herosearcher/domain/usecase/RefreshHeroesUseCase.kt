package com.example.vladshtuka.herosearcher.domain.usecase

import com.example.vladshtuka.herosearcher.domain.repository.HeroRepository

class RefreshHeroesUseCase(private val repository: HeroRepository) {

    suspend operator fun invoke() {
        val heroesList = repository.getHeroesFromApi()
        for (hero in heroesList) {
            if (repository.getHeroName(hero.name) == null) {
                repository.insertHero(hero)
            }
        }
    }
}