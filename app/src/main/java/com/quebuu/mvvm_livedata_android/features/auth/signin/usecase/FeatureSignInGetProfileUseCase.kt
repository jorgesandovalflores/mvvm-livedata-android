package com.quebuu.mvvm_livedata_android.features.auth.signin.usecase

import com.quebuu.mvvm_livedata_android.core.usecase.BaseUseCase
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInEntityRequest
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.repository.IFeatureSignInRepository

class FeatureSignInGetProfileUseCase(private val featureSignInRepository: IFeatureSignInRepository) :
    BaseUseCase<FeatureSignInEntityRequest, String>() {

    override suspend fun run(params: FeatureSignInEntityRequest): String {
        return featureSignInRepository.callGetProfile(params)
    }

}