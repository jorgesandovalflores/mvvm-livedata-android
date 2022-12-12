package com.quebuu.mvvm_livedata_android.features.auth.recovery.viewmodel

import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BasePresenterDelegate

class FeatureRecoveryPresenterDelegate(private val bindingDelegate: FeatureRecoveryBindingDelegate) :
    BasePresenterDelegate(bindingDelegate) {

    fun showUsername(value: String) {
        bindingDelegate.showUsernamePostValue(value)
    }

    fun successRecoveryPassword() {
        bindingDelegate.hideProgressViewPostValue()
        bindingDelegate.successRecoveryPasswordPostValue()
    }

    fun validateUsername(showError: Boolean) {
        bindingDelegate.validateUsernamePostValue(showError)
    }

}