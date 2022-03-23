package com.example.vladshtuka.herosearcher.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vladshtuka.herosearcher.domain.model.Hero
import com.example.vladshtuka.herosearcher.domain.usecase.HeroUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroSearchViewModel @Inject constructor(
    private val heroUseCases: HeroUseCases
) : ViewModel() {

    private val _matchedHeroesList = MutableLiveData<List<Hero>>()
    val matchedHeroesList: LiveData<List<Hero>>
        get() = _matchedHeroesList

    private val _navigateToHeroDetail = MutableLiveData<Hero?>()
    val navigateToHeroDetail: LiveData<Hero?>
        get() = _navigateToHeroDetail

    fun getMatchedNames(sequence: String) {
        viewModelScope.launch {
            val matchedNames = heroUseCases.getMatchedNamesUseCase(sequence)
            _matchedHeroesList.postValue(matchedNames)
        }
    }

    fun onHeroClicked(hero: Hero?) {
        _navigateToHeroDetail.value = hero
    }

    fun onHeroDetailNavigated() {
        _navigateToHeroDetail.value = null
    }

    fun insertHeroToFavorites(hero: Hero) {
        hero.isLiked = true
        viewModelScope.launch {
            heroUseCases.insertFavoriteHeroUseCase(hero)
        }
    }

    fun deleteHeroFromFavorites(hero: Hero) {
        hero.isLiked = false
        viewModelScope.launch {
            heroUseCases.deleteFavoriteHeroUseCase(hero.name)
        }
    }
}