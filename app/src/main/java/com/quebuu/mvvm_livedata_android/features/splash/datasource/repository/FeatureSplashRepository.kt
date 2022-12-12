package com.quebuu.mvvm_livedata_android.features.splash.datasource.repository

import com.quebuu.mvvm_livedata_android.features.splash.datasource.service.IFeatureSplashService
import com.quebuu.mvvm_livedata_android.features.splash.datasource.entity.FeatureSplashEntityRequest
import com.quebuu.mvvm_livedata_android.features.splash.usecase.FeatureSplashUseCaseModel
import com.quebuu.mvvm_livedata_android.features.splash.usecase.toUseCaseModel

class FeatureSplashRepository(private val iFeatureSplashService: IFeatureSplashService): IFeatureSplashRepository {
    @Throws(Exception::class)
    override suspend fun callGetSomeMethod(featureSplashEntityRequest: FeatureSplashEntityRequest): List<FeatureSplashUseCaseModel> {
        return iFeatureSplashService.getSomeMethod(featureSplashEntityRequest).map { it.toUseCaseModel() }
    }
}