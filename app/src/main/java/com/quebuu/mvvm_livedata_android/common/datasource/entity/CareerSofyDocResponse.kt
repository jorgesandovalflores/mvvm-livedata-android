package com.quebuu.mvvm_livedata_android.common.datasource.entity

import com.google.gson.annotations.SerializedName

data class TokenSofyDocResponse(
    @SerializedName("error") val error: Boolean = false,
    @SerializedName("message") val message: String = "",
    @SerializedName("data") val data: TokenSofyDocDataResponse = TokenSofyDocDataResponse(),
)

data class TokenSofyDocDataResponse(
    @SerializedName("carreras") val careers: List<CareerSofyDocResponse> = arrayListOf(),
    @SerializedName("token") val token: String = "",
)

data class CareerSofyDocResponse(
    @SerializedName("id") val id: String = "",
    @SerializedName("descripcion") val description: String = "",
    @SerializedName("token_carrera") val token: String = ""
)