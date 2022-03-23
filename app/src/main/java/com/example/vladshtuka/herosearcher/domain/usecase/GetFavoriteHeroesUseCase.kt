package com.example.vladshtuka.herosearcher.domain.usecase

import androidx.lifecycle.LiveData
import com.example.vladshtuka.herosearcher.domain.model.Hero
import com.example.vladshtuka.herosearcher.domain.repository.HeroRepository

class GetFavoriteHeroesUseCase(private val repository: HeroRepository) {

    operator fun invoke(): LiveData<List<Hero>> {
        return repository.getFavoriteHeroes()
    }

}