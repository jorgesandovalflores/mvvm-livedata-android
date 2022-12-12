package com.quebuu.mvvm_livedata_android.common.usecase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeatureModel(
    val idFeature: String = "",
    val idUser: String = "",
    val name: String = "",
    val description: String = "",
    val institution: String = "",
    val active: Boolean = true,
    val dateCreate: String = "",
    val dateUpdate: String = ""
): Parcelable