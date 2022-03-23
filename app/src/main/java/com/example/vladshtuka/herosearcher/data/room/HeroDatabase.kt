package com.example.vladshtuka.herosearcher.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
//import com.example.vladshtuka.herosearcher.domain.model.FavoriteHero
import com.example.vladshtuka.herosearcher.domain.model.Hero

@Database(entities = [Hero::class], version = 1, exportSchema = false)
abstract class HeroDatabase : RoomDatabase() {

    abstract val heroDao: HeroDao
//    abstract val favoriteHeroDao: FavoriteHeroDao

    companion object {
        const val DATABASE_NAME = "hero_database"
    }

}