package com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.repository

import com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.service.IFeatureRecoveryService
import com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.entity.FeatureRecoveryEntityRequest

class FeatureRecoveryRepository(private val iFeatureRecoveryService: IFeatureRecoveryService) :
    IFeatureRecoveryRepository {
    @Throws(Exception::class)
    override suspend fun callRecoveryPassword(featureRecoveryEntityRequest: FeatureRecoveryEntityRequest) {
        iFeatureRecoveryService.recoveryPassword(
            featureRecoveryEntityRequest.username,
            featureRecoveryEntityRequest.institution,
            featureRecoveryEntityRequest.username
        )
    }
}