package com.quebuu.mvvm_livedata_android.core.bases.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

abstract class BaseBindingDelegate{
    //region Generic actions

    //region Show progress view
    private val _showProgressView = MutableLiveData<Event<Unit>>()
    val showProgressView: LiveData<Event<Unit>> get() = _showProgressView
    fun showProgressViewPostValue() {
        _showProgressView.value = Event(Unit)
    }
    //endregion

    //region Hide progress view
    private val _hideProgressView = MutableLiveData<Event<Unit>>()
    val hideProgressView: LiveData<Event<Unit>> get() = _hideProgressView
    fun hideProgressViewPostValue() {
        _hideProgressView.value = Event(Unit)
    }
    //endregion

    //region Show Error Internet Connection
    private val _showErrorInternetConnection = MutableLiveData<Event<Unit>>()
    val showErrorInternetConnection: LiveData<Event<Unit>> get() = _showErrorInternetConnection
    fun showErrorInternetConnectionPostValue() {
        _showErrorInternetConnection.value = Event(Unit)
    }
    //endregion

    //region Hide Error Internet Connection
    private val _hideErrorInternetConnection = MutableLiveData<Event<Unit>>()
    val hideErrorInternetConnection: LiveData<Event<Unit>> get() = _hideErrorInternetConnection
    fun hideErrorInternetConnectionPostValue() {
        _hideErrorInternetConnection.value = Event(Unit)
    }
    //endregion

    //region Hide Generic error
    private val _hideGenericError = MutableLiveData<Event<Unit>>()
    val hideGenericError: LiveData<Event<Unit>> get() = _hideGenericError
    fun hideGenericErrorPostValue() {
        _hideGenericError.value = Event(Unit)
    }
    //endregion

    //region showErrorApi
    private val _showErrorApi = MutableLiveData<Event<String>>()
    val showErrorApi: LiveData<Event<String>> get() = _showErrorApi
    fun showErrorApiPostValue(value: String) {
        _showErrorApi.value = Event(value)
    }
    //endregion

    //region showGenericError
    private val _showGenericError = MutableLiveData<Event<Unit>>()
    val showGenericError: LiveData<Event<Unit>> get() = _showGenericError
    fun showGenericErrorPostValue() {
        _showGenericError.value = Event(Unit)
    }
    //endregion

    //region showGenericError
    private val _showUnauthorized = MutableLiveData<Event<Unit>>()
    val showUnauthorized: LiveData<Event<Unit>> get() = _showUnauthorized
    fun showUnauthorizedPostValue() {
        _showUnauthorized.value = Event(Unit)
    }
    //endregion

    //endregion
}