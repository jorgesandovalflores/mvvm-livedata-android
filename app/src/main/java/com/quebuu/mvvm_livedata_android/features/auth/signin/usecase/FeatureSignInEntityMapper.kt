package com.quebuu.mvvm_livedata_android.features.auth.signin.usecase

import com.quebuu.mvvm_livedata_android.common.usecase.UserInformationModel
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInInformationEntityResponse
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSigninEntityResponse

fun FeatureSignInInformationEntityResponse.toModel() = UserInformationModel(
    photoBytes = photoBytes ?: "",
    username = username ?: "",
    profile = profile ?: "",
    names = names,
    code = code,
    phone = phone ?: "",
    celPhone = celPhone ?: "",
    email = email ?: "",
    emailInstitutional = emailInstitutional,
    photo = photo ?: "",
    collegeCareer = collegeCareer
)

fun FeatureSigninEntityResponse.toModel() = FeatureSignInUseCaseModel(
    tokenWS, tokenBackend
)