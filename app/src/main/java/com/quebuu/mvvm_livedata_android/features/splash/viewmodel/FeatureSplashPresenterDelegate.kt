package com.quebuu.mvvm_livedata_android.features.splash.viewmodel

import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BasePresenterDelegate

class FeatureSplashPresenterDelegate(private val bindingDelegate: FeatureSplashBindingDelegate) :
    BasePresenterDelegate(bindingDelegate) {

    fun showCustomProgress() {
        bindingDelegate.showCustomProgressPostValue()
    }

    fun showHome() {
        bindingDelegate.hideCustomProgressPostValue()
        bindingDelegate.showHomePostValue()
    }

    fun showSignIn() {
        bindingDelegate.hideCustomProgressPostValue()
        bindingDelegate.showSignInPostValue()
    }
}