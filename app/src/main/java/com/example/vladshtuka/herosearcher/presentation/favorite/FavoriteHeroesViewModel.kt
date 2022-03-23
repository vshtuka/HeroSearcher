package com.example.vladshtuka.herosearcher.presentation.favorite

import androidx.lifecycle.*
import com.example.vladshtuka.herosearcher.domain.model.Hero
import com.example.vladshtuka.herosearcher.domain.usecase.HeroUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteHeroesViewModel @Inject constructor(
    private val heroUseCases: HeroUseCases
) : ViewModel() {
    val favoriteHeroesList = heroUseCases.getFavoriteHeroesUseCase()

    private val _navigateToHeroDetail = MutableLiveData<Hero?>()
    val navigateToHeroDetail: LiveData<Hero?>
        get() = _navigateToHeroDetail

    fun onHeroClicked(favoriteHero: Hero?) {
        _navigateToHeroDetail.value = favoriteHero
    }

    fun onHeroDetailNavigated() {
        _navigateToHeroDetail.value = null
    }

    fun deleteHeroFromFavorites(hero: Hero?) {
        viewModelScope.launch {
            heroUseCases.deleteFavoriteHeroUseCase(hero!!.name)
        }
    }

}