package com.example.vladshtuka.herosearcher.domain.usecase

data class HeroUseCases(
    val deleteFavoriteHeroUseCase: DeleteFavoriteHeroUseCase,
    val getFavoriteHeroesUseCase: GetFavoriteHeroesUseCase,
    val getMatchedNamesUseCase: GetMatchedNamesUseCase,
    val insertFavoriteHeroUseCase: InsertFavoriteHeroUseCase,
    val refreshHeroesUseCase: RefreshHeroesUseCase
)