package com.quebuu.mvvm_livedata_android.features.splash.usecase

import com.quebuu.mvvm_livedata_android.features.splash.datasource.entity.FeatureSplashEntityResponse
        
fun FeatureSplashEntityResponse.toUseCaseModel() = FeatureSplashUseCaseModel(
    someFieldExample = exampleField
)

fun FeatureSplashUseCaseModel.toEntityResponse() = FeatureSplashEntityResponse(
    exampleField = someFieldExample
)        