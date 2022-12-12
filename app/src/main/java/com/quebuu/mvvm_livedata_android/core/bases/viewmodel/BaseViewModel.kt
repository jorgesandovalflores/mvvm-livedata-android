package com.quebuu.mvvm_livedata_android.core.bases.viewmodel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel(
    open val bindingDelegate: BaseBindingDelegate,
    private val presentationDelegate: BasePresenterDelegate
) : ViewModel() {

}