package com.quebuu.mvvm_livedata_android.core.usecase

import com.google.gson.annotations.SerializedName

data class AppCustomErrorModel(
    @SerializedName("message") val message: String = "",
    @SerializedName("status_code") val statusCode: Int = 0
)