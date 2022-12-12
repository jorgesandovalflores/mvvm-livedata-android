package com.quebuu.mvvm_livedata_android.features.auth.signin.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.launch
import com.quebuu.mvvm_livedata_android.common.usecase.GetFeaturesUseCase
import com.quebuu.mvvm_livedata_android.common.usecase.SignInSofyDocUseCase
import com.quebuu.mvvm_livedata_android.core.bases.toBase64
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BaseViewModel
import com.quebuu.mvvm_livedata_android.core.repository.preferences.IPreferenceApp
import com.quebuu.mvvm_livedata_android.core.repository.preferences.IPreferenceUser
import com.quebuu.mvvm_livedata_android.core.usecase.support.BaseResultWrapper
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInEntityRequest
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInInformationEntityRequest
import com.quebuu.mvvm_livedata_android.features.auth.signin.usecase.FeatureSignInGetInformationUseCase
import com.quebuu.mvvm_livedata_android.features.auth.signin.usecase.FeatureSignInGetProfileUseCase
import com.quebuu.mvvm_livedata_android.features.auth.signin.usecase.FeatureSignInUseCase

class FeatureSignInViewModel(
    private val featureSignInUseCase: FeatureSignInUseCase,
    private val featureSignInGetProfileUseCase: FeatureSignInGetProfileUseCase,
    private val featureSignInGetInformationUseCase: FeatureSignInGetInformationUseCase,
    private val getFeaturesUseCase: GetFeaturesUseCase,
    private val signInSofyDocUseCase: SignInSofyDocUseCase,
    private val preferenceUser: IPreferenceUser,
    private val preferencesApp: IPreferenceApp,
    override val bindingDelegate: FeatureSignInBindingDelegate,
    val presenterDelegate: FeatureSignInPresenterDelegate = FeatureSignInPresenterDelegate(
        bindingDelegate
    )
) : BaseViewModel(bindingDelegate, presenterDelegate) {

    // method's call
    fun callGetUsername() {
        viewModelScope.launch {
            verifyGetUsername()
        }
    }

    fun callGetTerms() {
        viewModelScope.launch {
            verifyGetTerms()
        }
    }

    fun callSignIn(args: FeatureSignInEntityRequest, remember: Boolean) {
        viewModelScope.launch {
            verifySignIn(args = args, remember = remember)
        }
    }

    // method's private
    private suspend fun verifyGetUsername() {
        val username = preferencesApp.getUsername()
        if (!username.isNullOrEmpty()) {
            presenterDelegate.showUsername(username)
        }
    }

    private suspend fun verifyGetTerms() {
        val checkTerms = preferencesApp.getTerms()
        if (checkTerms) {
            presenterDelegate.hideTerms()
        } else {
            presenterDelegate.showTerms()
        }
    }

    private suspend fun verifySignIn(args: FeatureSignInEntityRequest, remember: Boolean) {
        presenterDelegate.showProgressView()
        when (val response = featureSignInUseCase.invoke(args)) {
            is BaseResultWrapper.ApiError -> presenterDelegate.showErrorApi(response.error)
            is BaseResultWrapper.ApiSuccess -> {
                // get profile
                args.tokenWS = response.value.tokenWS
                args.tokenBackend = response.value.tokenBackend
                verifyGetProfile(remember, args)
            }
        }
    }

    private suspend fun verifyGetProfile(remember: Boolean, args: FeatureSignInEntityRequest) {
        when (val response = featureSignInGetProfileUseCase.invoke(args)) {
            is BaseResultWrapper.ApiError -> presenterDelegate.showErrorApi(response.error)
            is BaseResultWrapper.ApiSuccess -> {
                // access from profile student
                if (response.value == "A") {
                    // save status
                    preferenceUser.setProfile(response.value)

                    // save token
                    preferencesApp.setTokenWS(args.tokenWS)
                    preferencesApp.setTokenBackend(args.tokenBackend)

                    // save remember
                    if (remember) preferencesApp.setUsername(args.username)

                    // save terms
                    preferencesApp.setTerms(true)

                    // save features
                    verifyFeatures()

                    // get information
                    verifyGetInformation(username = args.username, profile = response.value)
                } else {
                    presenterDelegate.showAccessProfileNotPermission()
                }
            }
        }
    }

    private suspend fun verifyGetInformation(username: String, profile: String) {
        when (val response = featureSignInGetInformationUseCase.invoke(
            FeatureSignInInformationEntityRequest(
                username = username,
                profile = profile
            )
        )) {
            is BaseResultWrapper.ApiError -> presenterDelegate.showErrorApi(response.error)
            is BaseResultWrapper.ApiSuccess -> {
                // save information
                preferenceUser.setUser(Gson().toJson(response.value))

                // finish
                presenterDelegate.successSignIn()
            }
        }
    }

    private suspend fun verifyFeatures() {
        when (val response = getFeaturesUseCase.invoke(Unit)) {
            is BaseResultWrapper.ApiSuccess -> {
                preferencesApp.setFeatures(Gson().toJson(response.value))
            }
        }
    }

    private suspend fun verifyAuthSofyDoc(username: String) {
        val token = "institucion=51&alumno=${username}".toBase64()
        Log.d("OkHttp", "token = ${token}")
        when (val response = signInSofyDocUseCase.invoke(token)) {
            is BaseResultWrapper.ApiSuccess -> {
                preferencesApp.setTokenSofyDoc(response.value.token)
                preferenceUser.setCareers(Gson().toJson(response.value.careers))

                // finish
                presenterDelegate.successSignIn()
            }
        }
    }

}