package com.quebuu.mvvm_livedata_android.features.splash.viewmodel

import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.quebuu.mvvm_livedata_android.common.usecase.GetFeaturesUseCase
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BaseViewModel
import com.quebuu.mvvm_livedata_android.core.repository.preferences.IPreferenceApp
import com.quebuu.mvvm_livedata_android.core.usecase.support.BaseResultWrapper

class FeatureSplashViewModel(
    private val getFeaturesUseCase: GetFeaturesUseCase,
    private val preferenceApp: IPreferenceApp,
    override val bindingDelegate: FeatureSplashBindingDelegate,
    private val presenterDelegate: FeatureSplashPresenterDelegate = FeatureSplashPresenterDelegate(
        bindingDelegate
    )
) : BaseViewModel(bindingDelegate, presenterDelegate) {

    fun callParameters() {
        viewModelScope.launch {
            verifyParameters()
        }
    }

    private suspend fun verifyParameters() {
        presenterDelegate.showCustomProgress()
        delay(1500)
        if (preferenceApp.getTokenWS().isNullOrEmpty()) {
            presenterDelegate.showSignIn()
        } else {
            verifyFeatures()
        }
    }

    private suspend fun verifyFeatures() {
        when(val response = getFeaturesUseCase.invoke(Unit)) {
            is BaseResultWrapper.ApiError -> presenterDelegate.showHome()
            is BaseResultWrapper.ApiSuccess -> {
                preferenceApp.setFeatures(Gson().toJson(response.value))
                presenterDelegate.showHome()
            }
        }
    }

}