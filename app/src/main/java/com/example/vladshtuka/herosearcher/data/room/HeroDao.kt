package com.example.vladshtuka.herosearcher.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vladshtuka.herosearcher.domain.model.Hero

@Dao
interface HeroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(hero: Hero)

    @Query("SELECT name FROM hero_table WHERE name = :name LIMIT 1")
    suspend fun getHeroName(name: String): String?

    @Query("UPDATE hero_table SET isLiked =:isLiked WHERE name = :name")
    suspend fun update(isLiked: Boolean, name: String)

    @Query("SELECT * FROM hero_table")
    suspend fun getAllHeroes() : List<Hero>

    @Query("SELECT * FROM hero_table WHERE isLiked == :isLiked")
    fun getFavoriteHeroes(isLiked: Boolean = true): LiveData<List<Hero>>
}