package com.example.vladshtuka.herosearcher.domain.usecase

import com.example.vladshtuka.herosearcher.domain.repository.HeroRepository

class DeleteFavoriteHeroUseCase(private val repository: HeroRepository) {

    suspend operator fun invoke(name: String) {
        repository.updateHero(false, name)
    }
}