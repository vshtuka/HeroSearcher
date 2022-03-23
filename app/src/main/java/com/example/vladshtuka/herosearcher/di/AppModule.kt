package com.example.vladshtuka.herosearcher.di

import android.app.Application
import androidx.room.Room
import com.example.vladshtuka.herosearcher.data.repository.HeroRepositoryImpl
import com.example.vladshtuka.herosearcher.data.retrofit.HeroApiService
import com.example.vladshtuka.herosearcher.data.room.HeroDatabase
import com.example.vladshtuka.herosearcher.domain.repository.HeroRepository
import com.example.vladshtuka.herosearcher.domain.usecase.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHeroDatabase(application: Application): HeroDatabase {
        return Room.databaseBuilder(
            application,
            HeroDatabase::class.java,
            HeroDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(HeroApiService.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): HeroApiService {
        return retrofit.create(HeroApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideHeroRepository(retrofitService: HeroApiService, database: HeroDatabase): HeroRepository {
        return HeroRepositoryImpl(retrofitService, database.heroDao)
    }

    @Provides
    @Singleton
    fun provideHeroUseCases(repository: HeroRepository): HeroUseCases {
        return HeroUseCases(
            deleteFavoriteHeroUseCase = DeleteFavoriteHeroUseCase(repository),
            getFavoriteHeroesUseCase = GetFavoriteHeroesUseCase(repository),
            getMatchedNamesUseCase = GetMatchedNamesUseCase(repository),
            insertFavoriteHeroUseCase = InsertFavoriteHeroUseCase(repository),
            refreshHeroesUseCase = RefreshHeroesUseCase(repository)
        )
    }

}