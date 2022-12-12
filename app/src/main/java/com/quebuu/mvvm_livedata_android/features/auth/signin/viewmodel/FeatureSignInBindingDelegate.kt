package com.quebuu.mvvm_livedata_android.features.auth.signin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BaseBindingDelegate
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.Event

class FeatureSignInBindingDelegate : BaseBindingDelegate() {

    //region showUsername
    private val _showUsername = MutableLiveData<Event<String>>()
    val showUsername: LiveData<Event<String>> get() = _showUsername
    fun showUsernamePostValue(value: String) {
        _showUsername.value = Event(value)
    }
    //endregion

    //region showTerms
    private val _showTerms = MutableLiveData<Event<Unit>>()
    val showTerms: LiveData<Event<Unit>> get() = _showTerms
    fun showTermsPostValue() {
        _showTerms.value = Event(Unit)
    }
    //endregion

    //region hideTerms
    private val _hideTerms = MutableLiveData<Event<Unit>>()
    val hideTerms: LiveData<Event<Unit>> get() = _hideTerms
    fun hideTermsPostValue() {
        _hideTerms.value = Event(Unit)
    }
    //endregion

    //region validateUsername
    private val _validateUsername = MutableLiveData<Event<Boolean>>()
    val validateUsername: LiveData<Event<Boolean>> get() = _validateUsername
    fun validateUsernamePostValue(showError: Boolean) {
        _validateUsername.value = Event(showError)
    }
    //endregion

    //region validatePassword
    private val _validatePassword = MutableLiveData<Event<Boolean>>()
    val validatePassword: LiveData<Event<Boolean>> get() = _validatePassword
    fun validatePasswordPostValue(showError: Boolean) {
        _validatePassword.value = Event(showError)
    }
    //endregion

    //region successSignIn
    private val _successSignIn = MutableLiveData<Event<Unit>>()
    val successSignIn: LiveData<Event<Unit>> get() = _successSignIn
    fun successSignInPostValue() {
        _successSignIn.value = Event(Unit)
    }
    //endregion

    //region showAccessProfileNotPermission
    private val _showAccessProfileNotPermission = MutableLiveData<Event<Unit>>()
    val showAccessProfileNotPermission: LiveData<Event<Unit>> get() = _showAccessProfileNotPermission
    fun showAccessProfileNotPermissionPostValue() {
        _showAccessProfileNotPermission.value = Event(Unit)
    }
    //endregion

}