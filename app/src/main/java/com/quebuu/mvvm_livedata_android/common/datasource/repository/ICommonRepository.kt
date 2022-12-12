package com.quebuu.mvvm_livedata_android.common.datasource.repository

import com.quebuu.mvvm_livedata_android.common.datasource.entity.CareerEntityRequest
import com.quebuu.mvvm_livedata_android.common.usecase.CareerModel
import com.quebuu.mvvm_livedata_android.common.usecase.CareerSofyDocDataModel
import com.quebuu.mvvm_livedata_android.common.usecase.FeatureModel

interface ICommonRepository {

    @Throws(Exception::class)
    suspend fun callGetCareers(request: CareerEntityRequest): List<CareerModel>

    @Throws(Exception::class)
    suspend fun callGetFeatures(): List<FeatureModel>

    @Throws(Exception::class)
    suspend fun callSignInSofyDoc(base64: String): CareerSofyDocDataModel

}