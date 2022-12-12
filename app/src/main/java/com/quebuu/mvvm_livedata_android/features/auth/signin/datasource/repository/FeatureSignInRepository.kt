package com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.repository

import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.service.IFeatureSignInService
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInEntityRequest
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInInformationEntityRequest
import com.quebuu.mvvm_livedata_android.common.usecase.UserInformationModel
import com.quebuu.mvvm_livedata_android.features.auth.signin.usecase.FeatureSignInUseCaseModel
import com.quebuu.mvvm_livedata_android.features.auth.signin.usecase.toModel

class FeatureSignInRepository(private val serviceJSONBACKEND: IFeatureSignInService, private val serviceJSONWS: IFeatureSignInService) :
    IFeatureSignInRepository {

    override suspend fun callSignIn(request: FeatureSignInEntityRequest): FeatureSignInUseCaseModel {
        return serviceJSONBACKEND.signIn(request).toModel()
    }

    override suspend fun callGetProfile(request: FeatureSignInEntityRequest): String {
        return serviceJSONWS.getStatus(request.username, request.password, request.institution, request.tokenWS).profile ?: ""
    }

    override suspend fun callGetInformation(request: FeatureSignInInformationEntityRequest): UserInformationModel {
        val response = serviceJSONWS.getInformation(request.username, request.profile)
        return if(response.isEmpty()) UserInformationModel() else response[0].toModel()
    }

}