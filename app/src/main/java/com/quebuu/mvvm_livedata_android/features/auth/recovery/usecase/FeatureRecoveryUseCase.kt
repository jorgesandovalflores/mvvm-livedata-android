package com.quebuu.mvvm_livedata_android.features.auth.recovery.usecase

import com.quebuu.mvvm_livedata_android.core.usecase.BaseUseCase
import com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.repository.IFeatureRecoveryRepository
import com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.entity.FeatureRecoveryEntityRequest

class FeatureRecoveryUseCase(private val featureRecoveryRepository: IFeatureRecoveryRepository) :
    BaseUseCase<FeatureRecoveryEntityRequest, Unit>() {

    override suspend fun run(params: FeatureRecoveryEntityRequest) {
        featureRecoveryRepository.callRecoveryPassword(params)
    }

}