package com.quebuu.mvvm_livedata_android.features.auth.recovery.view

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FeatureRecoveryViewInput(
    val username: String
): Parcelable