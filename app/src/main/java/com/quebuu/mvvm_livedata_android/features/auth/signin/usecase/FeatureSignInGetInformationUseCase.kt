package com.quebuu.mvvm_livedata_android.features.auth.signin.usecase

import com.quebuu.mvvm_livedata_android.common.usecase.UserInformationModel
import com.quebuu.mvvm_livedata_android.core.usecase.BaseUseCase
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInInformationEntityRequest
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.repository.IFeatureSignInRepository

class FeatureSignInGetInformationUseCase(private val featureSignInRepository: IFeatureSignInRepository) :
    BaseUseCase<FeatureSignInInformationEntityRequest, UserInformationModel>() {

    override suspend fun run(params: FeatureSignInInformationEntityRequest): UserInformationModel {
        return featureSignInRepository.callGetInformation(params)
    }
}