package com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity

import com.google.gson.annotations.SerializedName

data class FeatureSignInEntityRequest(
    @SerializedName("username") val username: String = "",
    @SerializedName("password") val password: String = "",
    @SerializedName("institution") val institution: String = "51",
    var tokenWS: String = "",
    var tokenBackend: String = ""
)