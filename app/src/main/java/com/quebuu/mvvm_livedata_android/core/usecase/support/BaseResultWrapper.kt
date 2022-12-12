package com.quebuu.mvvm_livedata_android.core.usecase.support

sealed class BaseResultWrapper<out T> {
    data class ApiSuccess<out T>(val value: T): BaseResultWrapper<T>()
    data class ApiError(val error: ErrorModel): BaseResultWrapper<Nothing>()
}