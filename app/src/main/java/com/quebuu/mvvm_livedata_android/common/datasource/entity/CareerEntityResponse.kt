package com.quebuu.mvvm_livedata_android.common.datasource.entity

import com.google.gson.annotations.SerializedName

data class CareerEntityResponse(
    @SerializedName("facultad") val faculty: String? = null,
    @SerializedName("codigofacultad") val facultyCode: String? = null,
    @SerializedName("grado") val grade: String? = null,
    @SerializedName("codigoGrado") val gradeCode: String? = null,
    @SerializedName("alumno") val student: String? = null,
    @SerializedName("mes") val month: String? = null,
    @SerializedName("tipo") val type: String? = null,
    @SerializedName("periodo") val period: String? = null,
    @SerializedName("descripcion_periodo") val periodDescription: String? = null,
    @SerializedName("codigo") val code: String? = null,
    @SerializedName("nombre") val name: String? = null,
    @SerializedName("carrera") val career: String? = null,
    @SerializedName("descripcion_carrera") val careerDescription: String? = null,
    @SerializedName("deuda") val due: String? = null,
)