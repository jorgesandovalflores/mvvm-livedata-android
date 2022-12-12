package com.quebuu.mvvm_livedata_android.common.usecase

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CareerModel(
    val faculty: String = "",
    val facultyCode: String = "",
    val grade: String = "",
    val gradeCode: String = "",
    val student: String = "",
    val month: String = "",
    val type: String = "",
    val period: String = "",
    var periods: ArrayList<PeriodModel> = ArrayList(),
    val periodDescription: String = "",
    val code: String = "",
    val name: String = "",
    val career: String = "",
    val careerDescription: String = "",
    val due: String = ""
): Parcelable

@Parcelize
data class PeriodModel(
    val period: String = "",
    val periodDescription: String = ""
): Parcelable