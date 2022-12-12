package com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity

import com.google.gson.annotations.SerializedName

data class FeatureSigninEntityResponse(
    @SerializedName("token_wc") val tokenWS: String = "",
    @SerializedName("token_backend") val tokenBackend: String = "",
)