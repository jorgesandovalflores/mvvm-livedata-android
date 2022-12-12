package com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.repository

import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInEntityRequest
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInInformationEntityRequest
import com.quebuu.mvvm_livedata_android.common.usecase.UserInformationModel
import com.quebuu.mvvm_livedata_android.features.auth.signin.usecase.FeatureSignInUseCaseModel

interface IFeatureSignInRepository {

    @Throws(Exception::class)
    suspend fun callSignIn(request: FeatureSignInEntityRequest): FeatureSignInUseCaseModel

    @Throws(Exception::class)
    suspend fun callGetProfile(request: FeatureSignInEntityRequest): String

    @Throws(Exception::class)
    suspend fun callGetInformation(request: FeatureSignInInformationEntityRequest): UserInformationModel

}