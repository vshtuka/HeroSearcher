package com.example.vladshtuka.herosearcher.domain.usecase

import com.example.vladshtuka.herosearcher.domain.model.Hero
import com.example.vladshtuka.herosearcher.domain.repository.HeroRepository

class InsertFavoriteHeroUseCase(private val repository: HeroRepository) {

    suspend operator fun invoke(hero: Hero) {
        hero.isLiked = true
        repository.updateHero(true, hero.name)
    }
}