package com.quebuu.mvvm_livedata_android.common.usecase

import com.quebuu.mvvm_livedata_android.common.datasource.entity.CareerEntityResponse
import com.quebuu.mvvm_livedata_android.common.datasource.entity.CareerSofyDocResponse
import com.quebuu.mvvm_livedata_android.common.datasource.entity.FeatureEntityResponse
import com.quebuu.mvvm_livedata_android.common.datasource.entity.TokenSofyDocResponse

fun CareerEntityResponse.toUseCaseModel() = CareerModel(
    faculty = faculty ?: "",
    facultyCode = facultyCode ?: "",
    grade = grade ?: "",
    gradeCode = gradeCode ?: "",
    student = student ?: "",
    month = month ?: "",
    type = type ?: "",
    period = period ?: "",
    periodDescription = periodDescription ?: "",
    code = code ?: "",
    name = name ?: "",
    career = career ?: "",
    careerDescription = careerDescription ?: "",
    due = due ?: ""
)

fun CareerSofyDocResponse.toUModel() = CareerSofyDocModel(
    id, description, token
)

fun FeatureEntityResponse.toModel() = FeatureModel(
    idFeature, idUser, name, description, institution, active, dateCreate, dateUpdate
)

fun TokenSofyDocResponse.toMod() = CareerSofyDocDataModel(
    careers = data.careers.toModels(),
    token = data.token
)

fun List<CareerEntityResponse>.toUseCaseModel() = transformListResponseToModel(iterator())
fun List<FeatureEntityResponse>.toCaseModel() = transformListFeatureResponseToModel(iterator())
fun List<CareerSofyDocResponse>.toModels() = transformListResponseToModels(iterator())

private fun transformListResponseToModel(iterator: Iterator<CareerEntityResponse>): List<CareerModel> {
    val list = mutableListOf<CareerModel>()
    while (iterator.hasNext()) {
        list.add(iterator.next().toUseCaseModel())
    }
    return list
}

private fun transformListFeatureResponseToModel(iterator: Iterator<FeatureEntityResponse>): List<FeatureModel> {
    val list = mutableListOf<FeatureModel>()
    while (iterator.hasNext()) {
        list.add(iterator.next().toModel())
    }
    return list
}

private fun transformListResponseToModels(iterator: Iterator<CareerSofyDocResponse>): List<CareerSofyDocModel> {
    val list = mutableListOf<CareerSofyDocModel>()
    while (iterator.hasNext()) {
        list.add(iterator.next().toUModel())
    }
    return list
}

