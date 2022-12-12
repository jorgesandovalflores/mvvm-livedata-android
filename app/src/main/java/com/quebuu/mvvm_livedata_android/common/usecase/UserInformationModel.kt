package com.quebuu.mvvm_livedata_android.common.usecase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserInformationModel(
    val photoBytes: String = "",
    val username: String = "",
    val profile: String = "",
    val names: String = "",
    val code: String = "",
    val phone: String = "",
    val celPhone: String = "",
    val email: String = "",
    val emailInstitutional: String = "",
    val photo: String = "",
    val collegeCareer: String = "",
) : Parcelable