package com.example.vladshtuka.herosearcher.presentation.detail

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
class HeroDetailViewModel @Inject constructor(
    private val heroesUseCases: HeroUseCases
) : ViewModel() {
    private val _isLike = MutableLiveData<Boolean>()
    val isLike: LiveData<Boolean>
        get() = _isLike

    fun initLikeButton(hero: Hero) {
        viewModelScope.launch {
            if (hero.isLiked) {
                _isLike.postValue(true)
            } else {
                _isLike.postValue(false)
            }
        }
    }

    fun deleteHeroFromFavorites(hero: Hero) {
        _isLike.postValue(false)
        viewModelScope.launch {
            heroesUseCases.deleteFavoriteHeroUseCase(hero.name)
        }
    }

    fun insertHeroToFavorites(hero: Hero) {
        _isLike.postValue(true)
        viewModelScope.launch {
            heroesUseCases.insertFavoriteHeroUseCase(hero)
        }
    }
}