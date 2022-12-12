package com.quebuu.mvvm_livedata_android.common.datasource.entity

import com.google.gson.annotations.SerializedName

data class FeatureEntityResponse(
    @SerializedName("id_feature") val idFeature: String = "",
    @SerializedName("id_user") val idUser: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("institution") val institution: String = "",
    @SerializedName("active") val active: Boolean = true,
    @SerializedName("date_create") val dateCreate: String = "",
    @SerializedName("date_update") val dateUpdate: String = ""
)