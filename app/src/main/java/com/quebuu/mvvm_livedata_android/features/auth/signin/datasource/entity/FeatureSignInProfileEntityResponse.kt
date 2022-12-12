package com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity

import com.google.gson.annotations.SerializedName

data class FeatureSignInProfileEntityResponse(
    @SerializedName("usuario") val user: String = "",
    @SerializedName("password") val password: String = "",
    @SerializedName("status") val profile: String? = null
)