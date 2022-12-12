package com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity

import com.google.gson.annotations.SerializedName

data class FeatureSignInInformationEntityResponse(
    @SerializedName("fotoBytes") val photoBytes: String? = null,
    @SerializedName("usuario") val username: String? = null,
    @SerializedName("perfil") val profile: String? = null,
    @SerializedName("nombre_completo") val names: String = "",
    @SerializedName("codigo_alumno") val code: String = "",
    @SerializedName("telefono") val phone: String? = null,
    @SerializedName("celular") val celPhone: String? = null,
    @SerializedName("correo_personal") val email: String? = null,
    @SerializedName("correo_institucional") val emailInstitutional: String = "",
    @SerializedName("foto") val photo: String? = null,
    @SerializedName("carrera") val collegeCareer: String = "",
)