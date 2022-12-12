package com.quebuu.mvvm_livedata_android.features.splash.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BaseBindingDelegate
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.Event

class FeatureSplashBindingDelegate : BaseBindingDelegate() {

    //region showCustomProgress
    private val _showCustomProgress = MutableLiveData<Event<Unit>>()
    val showCustomProgress: LiveData<Event<Unit>> get() = _showCustomProgress
    fun showCustomProgressPostValue() {
        _showCustomProgress.value = Event(Unit)
    }
    //endregion

    //region hideCustomProgress
    private val _hideCustomProgress = MutableLiveData<Event<Unit>>()
    val hideCustomProgress: LiveData<Event<Unit>> get() = _hideCustomProgress
    fun hideCustomProgressPostValue() {
        _hideCustomProgress.value = Event(Unit)
    }
    //endregion

    //region showHome
    private val _showHome = MutableLiveData<Event<Unit>>()
    val showHome: LiveData<Event<Unit>> get() = _showHome
    fun showHomePostValue() {
        _showHome.value = Event(Unit)
    }
    //endregion

    //region showSignIn
    private val _showSignIn = MutableLiveData<Event<Unit>>()
    val showSignIn: LiveData<Event<Unit>> get() = _showSignIn
    fun showSignInPostValue() {
        _showSignIn.value = Event(Unit)
    }
    //endregion

}