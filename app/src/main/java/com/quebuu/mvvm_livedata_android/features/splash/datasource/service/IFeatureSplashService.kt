package com.quebuu.mvvm_livedata_android.features.splash.datasource.service

import com.quebuu.mvvm_livedata_android.features.splash.datasource.entity.FeatureSplashEntityResponse
import com.quebuu.mvvm_livedata_android.features.splash.datasource.entity.FeatureSplashEntityRequest
import retrofit2.http.GET
import retrofit2.http.Query

interface IFeatureSplashService {

    //Example api client method
    @GET("qry/api-example/some-method")
    suspend fun getSomeMethod(@Query("request") entityRequest: FeatureSplashEntityRequest): List<FeatureSplashEntityResponse>
}        