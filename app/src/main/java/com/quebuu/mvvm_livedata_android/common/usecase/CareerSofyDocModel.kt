package com.quebuu.mvvm_livedata_android.common.usecase

data class CareerSofyDocDataModel(
    val careers: List<CareerSofyDocModel> = arrayListOf(),
    val token: String = ""
)

data class CareerSofyDocModel(
    val id: String = "",
    val description: String = "",
    val token: String = ""
)