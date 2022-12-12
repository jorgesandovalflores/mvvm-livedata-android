package com.quebuu.mvvm_livedata_android.features.auth.recovery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BaseBindingDelegate
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.Event

class FeatureRecoveryBindingDelegate : BaseBindingDelegate() {

    //region showUsername
    private val _showUsername = MutableLiveData<Event<String>>()
    val showUsername: LiveData<Event<String>> get() = _showUsername
    fun showUsernamePostValue(value: String) {
        _showUsername.value = Event(value)
    }
    //endregion

    //region successRecoveryPassword
    private val _successRecoveryPassword = MutableLiveData<Event<Unit>>()
    val successRecoveryPassword: LiveData<Event<Unit>> get() = _successRecoveryPassword
    fun successRecoveryPasswordPostValue() {
        _successRecoveryPassword.value = Event(Unit)
    }
    //endregion

    //region validateUsername
    private val _validateUsername = MutableLiveData<Event<Boolean>>()
    val validateUsername: LiveData<Event<Boolean>> get() = _validateUsername
    fun validateUsernamePostValue(showError: Boolean) {
        _validateUsername.value = Event(showError)
    }
    //endregion

}