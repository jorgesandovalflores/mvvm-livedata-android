package com.quebuu.mvvm_livedata_android.common.datasource.service

import com.quebuu.mvvm_livedata_android.common.datasource.entity.CareerEntityResponse
import com.quebuu.mvvm_livedata_android.common.datasource.entity.FeatureEntityResponse
import com.quebuu.mvvm_livedata_android.common.datasource.entity.TokenSofyDocResponse
import retrofit2.http.*

interface ICommonService {

    @GET("getCarrera")
    suspend fun getCareers(
        @Query("institucion") institution: String,
        @Query("alumno") username: String,
        @Query("tipo") profile: String
    ): List<CareerEntityResponse>

    //Example api client method
    @GET("v1.0.0/feature/institution/{institution}")
    suspend fun getFeatures(
        @Path("institution") institution: String
    ): List<FeatureEntityResponse>

    //Example api client method
    @POST("app/public/wiener/generar-token")
    suspend fun signInSofyDoc(
        @Header("Token-Wiener") tokenWiener: String
    ): TokenSofyDocResponse
}