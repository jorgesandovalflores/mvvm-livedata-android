package com.quebuu.mvvm_livedata_android.core.bases.viewmodel

import com.google.gson.Gson
import com.quebuu.mvvm_livedata_android.core.usecase.AppCustomErrorModel
import com.quebuu.mvvm_livedata_android.core.usecase.support.ErrorModel

abstract class BasePresenterDelegate(private val bindingDelegate: BaseBindingDelegate) {

    fun showGenericError() {
        bindingDelegate.hideProgressViewPostValue()
        bindingDelegate.hideErrorInternetConnectionPostValue()
        bindingDelegate.hideGenericErrorPostValue()
    }

    fun showErrorApi(error: ErrorModel) {
        bindingDelegate.hideProgressViewPostValue()
        if (error.errorStatus == ErrorModel.ErrorStatus.APP_CUSTOM_ERROR) {
            val appCustomErrorModel =
                Gson().fromJson(error.message, AppCustomErrorModel::class.java)
            bindingDelegate.showErrorApiPostValue(appCustomErrorModel.message)
            return
        }
        if (error.errorStatus == ErrorModel.ErrorStatus.NO_CONNECTION) {
            bindingDelegate.showErrorInternetConnectionPostValue()
        } else if (error.errorStatus == ErrorModel.ErrorStatus.UNAUTHORIZED) {
            bindingDelegate.showUnauthorizedPostValue()
        } else {
            bindingDelegate.showGenericErrorPostValue()
        }
    }

    fun showInternetConnectionError() {
        bindingDelegate.showErrorInternetConnectionPostValue()
    }

    fun showProgressView() {
        bindingDelegate.showProgressViewPostValue()
    }

    fun hideProgressView() {
        bindingDelegate.hideProgressViewPostValue()
    }

}