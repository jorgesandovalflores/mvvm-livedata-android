package com.quebuu.mvvm_livedata_android.features.splash.datasource.repository

import com.quebuu.mvvm_livedata_android.features.splash.datasource.entity.FeatureSplashEntityRequest
import com.quebuu.mvvm_livedata_android.features.splash.usecase.FeatureSplashUseCaseModel

interface IFeatureSplashRepository {
    @Throws(Exception::class)
    suspend fun callGetSomeMethod(featureSplashEntityRequest: FeatureSplashEntityRequest): List<FeatureSplashUseCaseModel>
}        