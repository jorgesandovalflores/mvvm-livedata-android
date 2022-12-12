package com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.repository

import com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.entity.FeatureRecoveryEntityRequest

interface IFeatureRecoveryRepository {
    @Throws(Exception::class)
    suspend fun callRecoveryPassword(featureRecoveryEntityRequest: FeatureRecoveryEntityRequest)
}        