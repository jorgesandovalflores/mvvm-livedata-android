package com.quebuu.mvvm_livedata_android.common.usecase

import com.quebuu.mvvm_livedata_android.common.datasource.repository.ICommonRepository
import com.quebuu.mvvm_livedata_android.core.usecase.BaseUseCase

class GetFeaturesUseCase(private val commonRepository: ICommonRepository) :
    BaseUseCase<Unit, List<FeatureModel>>() {

    override suspend fun run(params: Unit): List<FeatureModel> {
        return commonRepository.callGetFeatures()
    }

}