package com.quebuu.mvvm_livedata_android

import android.app.Application
import android.os.Build
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import com.quebuu.mvvm_livedata_android.common.module.commonModule
import com.quebuu.mvvm_livedata_android.core.repository.api.apiModule
import com.quebuu.mvvm_livedata_android.core.repository.preferences.preferencesModule
import com.quebuu.mvvm_livedata_android.features.auth.recovery.module.featureRecoveryModule
import com.quebuu.mvvm_livedata_android.features.auth.signin.module.featureSignInModule
import com.quebuu.mvvm_livedata_android.features.splash.module.featureSplashModule
import mvvm_livedata_android.BuildConfig

class QuebuuApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.INFO)
            androidContext(this@QuebuuApplication)
            koin.loadModules(
                listOf(
                    // Modules core
                    preferencesModule,
                    apiModule,

                    // Modules common
                    commonModule,

                    // Modules features
                    featureSplashModule,
                    featureSignInModule,
                    featureRecoveryModule,
                )
            )
            koin.createRootScope()
        }
        getKoin().setProperty(BASE_URL_WC, BuildConfig.BASE_URL_WC)
        getKoin().setProperty(BASE_URL_BACKEND, BuildConfig.BASE_URL_BACKEND)
        getKoin().setProperty(BASE_URL_SOFYDOC, BuildConfig.BASE_URL_SOFYDOC)
        AndroidThreeTen.init(this)
    }

    private fun deviceIsOreoOrAbove(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    }

    companion object {
        const val BASE_URL_WC = "BASE_URL_WC"
        const val BASE_URL_BACKEND = "BASE_URL_BACKEND"
        const val BASE_URL_SOFYDOC = "BASE_URL_SOFYDOC"
    }
}