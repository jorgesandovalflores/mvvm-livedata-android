package com.quebuu.mvvm_livedata_android.core.repository.api

import android.content.Context
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import mvvm_livedata_android.BuildConfig
import com.quebuu.mvvm_livedata_android.QuebuuApplication
import com.quebuu.mvvm_livedata_android.core.repository.preferences.PreferenceApp
import com.quebuu.mvvm_livedata_android.core.repository.preferences.PreferencesManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val RETROFIT_API_JSON_WS = "RetrofitJsonWS"
const val RETROFIT_API_JSON_BACKEND = "RetrofitJsonBACKEND"
const val RETROFIT_API_JSON_SOFYDOC = "RetrofitJsonSOFYDOC"

val apiModule = module {
    single { providerHttpLoggingInterceptor() }
    single { providerCache(get()) }
    single { ApiInterceptor(get()) }
    single(named("CLIENT_INTERCEPT")) { providerOkHttpClient(get(), get()) }
    single(named("CLIENT_NOT_INTERCEPT")) { providerOkHttpClientNotInterceptor(get()) }
    single(named(RETROFIT_API_JSON_WS)) { providerRetrofitJson(
        baseUrl = getProperty(QuebuuApplication.BASE_URL_WC),
        client = get(named("CLIENT_INTERCEPT"))) }
    single(named(RETROFIT_API_JSON_BACKEND)) { providerRetrofitJson(
        baseUrl = getProperty(QuebuuApplication.BASE_URL_BACKEND),
        client = get(named("CLIENT_INTERCEPT"))) }
    single(named(RETROFIT_API_JSON_SOFYDOC)) { providerRetrofitJson(
        baseUrl = getProperty(QuebuuApplication.BASE_URL_SOFYDOC),
        client = get(named("CLIENT_NOT_INTERCEPT"))) }
}

fun providerRetrofitJson(baseUrl: String, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build()
}

fun providerOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    apiInterceptor: ApiInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(30L, TimeUnit.SECONDS)
        .writeTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(ApiBodyInterceptor())
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(apiInterceptor)
        .build()
}

fun providerOkHttpClientNotInterceptor(
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(30L, TimeUnit.SECONDS)
        .writeTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(ApiBodyInterceptor())
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun providerCache(context: Context): Cache {
    val cacheSize: Long = 10485760
    return Cache(context.cacheDir, cacheSize)
}

fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}

class ApiInterceptor(private val preferencesManager: PreferencesManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val tokenWS = preferencesManager.getString(PreferenceApp.KEY_TOKEN_WS_DATA)
        val tokenBackend = preferencesManager.getString(PreferenceApp.KEY_TOKEN_BACKEND_DATA)
        if (tokenWS.isNullOrEmpty()) {
            request = request.newBuilder()
                .header("Content-Type", "application/json; charset=utf-8")
                .build()
        } else {
            request = request.newBuilder()
                .header("token", tokenWS)
                .header("Authorization", "Bearer $tokenBackend")
                .header("Content-Type", "application/json; charset=utf-8")
                .build()
        }
        return chain.proceed(request)
    }
}