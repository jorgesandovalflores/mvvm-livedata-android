package com.quebuu.mvvm_livedata_android.features.splash.datasource.entity

import com.google.gson.annotations.SerializedName

data class FeatureSplashEntityRequest(
    @SerializedName("example_field")  val exampleField : String = ""
)