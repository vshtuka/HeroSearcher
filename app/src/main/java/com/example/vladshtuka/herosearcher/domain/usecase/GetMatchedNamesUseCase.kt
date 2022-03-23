package com.example.vladshtuka.herosearcher.domain.usecase

import com.example.vladshtuka.herosearcher.domain.model.Hero
import com.example.vladshtuka.herosearcher.domain.repository.HeroRepository

class GetMatchedNamesUseCase(private val repository: HeroRepository) {

    suspend operator fun invoke(sequence: String): List<Hero> {
        val matchedNames = mutableListOf<Hero>()
        val heroesList = repository.getAllHeroes()
        for (i in heroesList) {
            val heroName = i.name.lowercase().filter { !it.isWhitespace() }
            if (sequence.isNotEmpty() && heroName.contains(sequence.lowercase())) {
                matchedNames.add(i)
            }
        }

        return matchedNames
    }
}