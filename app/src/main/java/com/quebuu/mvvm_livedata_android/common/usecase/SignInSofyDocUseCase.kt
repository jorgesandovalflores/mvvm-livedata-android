package com.quebuu.mvvm_livedata_android.common.usecase

import com.quebuu.mvvm_livedata_android.common.datasource.repository.ICommonRepository
import com.quebuu.mvvm_livedata_android.core.usecase.BaseUseCase

class SignInSofyDocUseCase(private val commonRepository: ICommonRepository) : BaseUseCase<String, CareerSofyDocDataModel>() {

    override suspend fun run(params: String): CareerSofyDocDataModel {
        return commonRepository.callSignInSofyDoc(params)
    }

}