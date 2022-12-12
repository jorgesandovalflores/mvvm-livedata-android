package com.quebuu.mvvm_livedata_android.features.auth.signin.usecase

import com.quebuu.mvvm_livedata_android.core.usecase.BaseUseCase
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.repository.IFeatureSignInRepository
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInEntityRequest

class FeatureSignInUseCase(private val featureSignInRepository: IFeatureSignInRepository) :
    BaseUseCase<FeatureSignInEntityRequest, FeatureSignInUseCaseModel>() {

    override suspend fun run(params: FeatureSignInEntityRequest): FeatureSignInUseCaseModel {
        return featureSignInRepository.callSignIn(params)
    }

}