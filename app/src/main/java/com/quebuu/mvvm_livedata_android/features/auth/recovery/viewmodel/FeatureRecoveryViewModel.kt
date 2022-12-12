package com.quebuu.mvvm_livedata_android.features.auth.recovery.viewmodel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BaseViewModel
import com.quebuu.mvvm_livedata_android.core.usecase.support.BaseResultWrapper
import com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.entity.FeatureRecoveryEntityRequest
import com.quebuu.mvvm_livedata_android.features.auth.recovery.usecase.FeatureRecoveryUseCase

class FeatureRecoveryViewModel(
    private val featureRecoveryUseCase: FeatureRecoveryUseCase,
    override val bindingDelegate: FeatureRecoveryBindingDelegate,
    val presenterDelegate: FeatureRecoveryPresenterDelegate = FeatureRecoveryPresenterDelegate(
        bindingDelegate
    )
) : BaseViewModel(bindingDelegate, presenterDelegate) {

    fun callRecoveryPassword(request: FeatureRecoveryEntityRequest) {
        viewModelScope.launch {
            verifyRecoveryPassword(request)
        }
    }

    private suspend fun verifyRecoveryPassword(request: FeatureRecoveryEntityRequest) {
        presenterDelegate.showProgressView()
        when(val response = featureRecoveryUseCase.invoke(request)) {
            is BaseResultWrapper.ApiError -> presenterDelegate.showErrorApi(response.error)
            is BaseResultWrapper.ApiSuccess -> presenterDelegate.successRecoveryPassword()
        }
    }

}