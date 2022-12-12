package com.quebuu.mvvm_livedata_android.core.repository.preferences

class PreferenceUser(
    private val preferencesManager: PreferencesManager
) : IPreferenceUser {

    override fun setUser(json: String) {
        preferencesManager.setValue(KEY_USER_DATA, json)
    }

    override fun getUser(): String {
        return preferencesManager.getString(KEY_USER_DATA) ?: ""
    }

    override fun setProfile(profile: String) {
        preferencesManager.setValue(KEY_USER_PROFILE, profile)
    }

    override fun getProfile(): String {
        return preferencesManager.getString(KEY_USER_PROFILE) ?: ""
    }

    override fun setImage(base64: String) {
        preferencesManager.setValue(KEY_USER_IMAGE, base64)
    }

    override fun getImage(): String {
        return preferencesManager.getString(KEY_USER_IMAGE) ?: ""
    }

    override fun setCareers(json: String) {
        preferencesManager.setValue(KEY_CAREERS, json)
    }

    override fun getCareers(): String {
        return preferencesManager.getString(KEY_CAREERS) ?: "[]"
    }

    companion object {
        const val KEY_USER_DATA = "${PreferencesManager.NAME_FILE}_KEY_USER_DATA"
        const val KEY_USER_PROFILE = "${PreferencesManager.NAME_FILE}_KEY_USER_PROFILE"
        const val KEY_USER_IMAGE = "${PreferencesManager.NAME_FILE}_KEY_USER_IMAGE"
        const val KEY_CAREERS = "${PreferencesManager.NAME_FILE}_KEY_USER_CAREERS"
    }
}

interface IPreferenceUser {
    fun setUser(json: String)
    fun getUser(): String
    fun setProfile(status: String)
    fun getProfile(): String
    fun setImage(base64: String)
    fun getImage(): String
    fun setCareers(json: String)
    fun getCareers(): String
}