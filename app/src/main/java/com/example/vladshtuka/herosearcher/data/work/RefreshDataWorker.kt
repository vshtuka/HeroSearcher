package com.example.vladshtuka.herosearcher.data.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.vladshtuka.herosearcher.domain.usecase.HeroUseCases
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import retrofit2.HttpException

@HiltWorker
class RefreshDataWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val heroUseCases: HeroUseCases,
) :
    CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {

        try {
            heroUseCases.refreshHeroesUseCase()
        } catch (e: HttpException) {
            return Result.retry()
        }
        return Result.success()
    }

    companion object {
        const val WORK_NAME = "com.example.vladshtuka.herosearcher.data.work.RefreshDataWorker"
    }
}