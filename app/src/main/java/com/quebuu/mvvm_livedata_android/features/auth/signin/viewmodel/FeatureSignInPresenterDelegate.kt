package com.quebuu.mvvm_livedata_android.features.auth.signin.viewmodel

import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BasePresenterDelegate

class FeatureSignInPresenterDelegate(private val bindingDelegate: FeatureSignInBindingDelegate) :
    BasePresenterDelegate(bindingDelegate) {

    fun showUsername(value: String) {
        bindingDelegate.showUsernamePostValue(value)
    }

    fun showTerms() {
        bindingDelegate.showTermsPostValue()
    }

    fun hideTerms() {
        bindingDelegate.hideTermsPostValue()
    }

    fun validateUsername(showError: Boolean) {
        bindingDelegate.validateUsernamePostValue(showError)
    }

    fun validatePassword(showError: Boolean) {
        bindingDelegate.validatePasswordPostValue(showError)
    }

    fun successSignIn() {
        bindingDelegate.hideProgressViewPostValue()
        bindingDelegate.successSignInPostValue()
    }

    fun showAccessProfileNotPermission() {
        bindingDelegate.hideProgressViewPostValue()
        bindingDelegate.showAccessProfileNotPermissionPostValue()
    }

}