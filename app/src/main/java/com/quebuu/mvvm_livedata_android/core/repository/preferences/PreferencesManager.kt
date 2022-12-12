package com.quebuu.mvvm_livedata_android.core.repository.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class PreferencesManager(
    context: Context,
    private var encryptedPreferences: SharedPreferences = initNormal(context)) {

    fun setValue(key: String, value: String) {
        encryptedPreferences.edit().putString(key, value).apply()
    }

    fun setValue(key: String, value: Int) {
        encryptedPreferences.edit().putInt(key, value).apply()
    }

    fun setValue(key: String, value: Boolean) {
        encryptedPreferences.edit().putBoolean(key, value).apply()
    }

    fun setValue(key: String, value: Long) {
        encryptedPreferences.edit().putLong(key, value).apply()
    }

    fun setValue(key: String, value: Float) {
        encryptedPreferences.edit().putFloat(key, value).apply()
    }

    fun getString(key: String): String? {
        return encryptedPreferences.getString(key, "")
    }

    fun getInteger(key: String): Int {
        return encryptedPreferences.getInt(key, 0)
    }

    fun getLong(key: String): Long {
        return encryptedPreferences.getLong(key, 0)
    }

    fun getBoolean(key: String): Boolean {
        return encryptedPreferences.getBoolean(key, false)
    }

    fun getFloat(key: String): Float {
        return encryptedPreferences.getFloat(key, 0f)
    }

    companion object {
        const val NAME_FILE = "app-mvvm_livedata_android-pf"
    }
}

fun initJetPackSecurity(context: Context): SharedPreferences {
    val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    return EncryptedSharedPreferences.create(
        context,
        PreferencesManager.NAME_FILE,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

fun initNormal(context: Context): SharedPreferences {
    return context.getSharedPreferences(PreferencesManager.NAME_FILE, Context.MODE_PRIVATE)
}
