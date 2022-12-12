package com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.service

import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInEntityRequest
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInInformationEntityResponse
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInProfileEntityResponse
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSigninEntityResponse
import retrofit2.http.*

interface IFeatureSignInService {

    @POST("v1.0.0/user/signin")
    suspend fun signIn(
        @Body request: FeatureSignInEntityRequest
    ): FeatureSigninEntityResponse

    @GET("getLogin")
    suspend fun getStatus(
        @Query("codigo") username: String,
        @Query("pass") password: String,
        @Query("institucion") institution: String,
        @Header("token") token: String
    ): FeatureSignInProfileEntityResponse

    @GET("getDatosPersonales")
    suspend fun getInformation(
        @Query("usuario") username: String,
        @Query("perfil") profile: String
    ): List<FeatureSignInInformationEntityResponse>

}