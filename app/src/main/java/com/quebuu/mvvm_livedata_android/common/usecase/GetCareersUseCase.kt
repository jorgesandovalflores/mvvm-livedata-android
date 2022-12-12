package com.quebuu.mvvm_livedata_android.common.usecase

import com.quebuu.mvvm_livedata_android.common.datasource.entity.CareerEntityRequest
import com.quebuu.mvvm_livedata_android.common.datasource.repository.ICommonRepository
import com.quebuu.mvvm_livedata_android.core.usecase.BaseUseCase

class GetCareersUseCase(private val commonRepository: ICommonRepository) :
    BaseUseCase<CareerEntityRequest, List<CareerModel>>() {

    override suspend fun run(params: CareerEntityRequest): List<CareerModel> {
        return commonRepository.callGetCareers(params)
    }

}