package com.quebuu.mvvm_livedata_android.features.auth.recovery.usecase

import com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.entity.FeatureRecoveryEntityResponse
        
fun FeatureRecoveryEntityResponse.toUseCaseModel() = FeatureRecoveryUseCaseModel(
    someFieldExample = exampleField
)

fun FeatureRecoveryUseCaseModel.toEntityResponse() = FeatureRecoveryEntityResponse(
    exampleField = someFieldExample
)        