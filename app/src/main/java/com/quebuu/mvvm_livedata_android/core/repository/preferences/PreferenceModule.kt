package com.quebuu.mvvm_livedata_android.core.repository.preferences

import android.content.Context
import org.koin.dsl.module

val preferencesModule = module{
    single { providerPreferencesManager(context = get()) }
    single { providerPreferenceApp(preferencesManager = get()) }
    single { providerPreferenceUser(preferencesManager = get())}
}

fun providerPreferencesManager(context: Context): PreferencesManager {
    return PreferencesManager(context)
}

fun providerPreferenceApp(preferencesManager: PreferencesManager): IPreferenceApp {
    return PreferenceApp(preferencesManager)
}

fun providerPreferenceUser(preferencesManager: PreferencesManager): IPreferenceUser {
    return PreferenceUser(preferencesManager)
}