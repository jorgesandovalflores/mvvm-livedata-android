package com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.service

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface IFeatureRecoveryService {

    @GET("getOlvideClave")
    suspend fun recoveryPassword(
        @Query("usuario") username: String,
        @Query("institution") institution: String,
        @Header("token") token: String
    )

}