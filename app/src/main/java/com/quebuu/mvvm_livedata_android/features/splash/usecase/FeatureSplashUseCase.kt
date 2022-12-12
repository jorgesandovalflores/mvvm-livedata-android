package com.quebuu.mvvm_livedata_android.features.splash.usecase

import com.quebuu.mvvm_livedata_android.core.usecase.BaseUseCase
import com.quebuu.mvvm_livedata_android.features.splash.datasource.repository.IFeatureSplashRepository
import com.quebuu.mvvm_livedata_android.features.splash.datasource.entity.FeatureSplashEntityRequest
        
class FeatureSplashUseCase(private val featureSplashRepository: IFeatureSplashRepository) :
        BaseUseCase<FeatureSplashEntityRequest, List<FeatureSplashUseCaseModel>>() {

    override suspend fun run(params: FeatureSplashEntityRequest): List<FeatureSplashUseCaseModel>{
        return featureSplashRepository.callGetSomeMethod(params)
    }
}