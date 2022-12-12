package com.quebuu.mvvm_livedata_android.core.repository.preferences

class PreferenceApp(
    private val preferencesManager: PreferencesManager
) : IPreferenceApp {

    override fun setTokenWS(token: String) {
        preferencesManager.setValue(KEY_TOKEN_WS_DATA, token)
    }

    override fun getTokenWS(): String {
        return preferencesManager.getString(KEY_TOKEN_WS_DATA) ?: ""
    }

    override fun setTokenBackend(token: String) {
        preferencesManager.setValue(KEY_TOKEN_BACKEND_DATA, token)
    }

    override fun getTokenBackend(): String {
        return preferencesManager.getString(KEY_TOKEN_BACKEND_DATA) ?: ""
    }

    override fun setTokenSofyDoc(token: String) {
        preferencesManager.setValue(KEY_TOKEN_SOFYDOC_DATA, token)
    }

    override fun getTokenSofyDoc(): String {
        return preferencesManager.getString(KEY_TOKEN_SOFYDOC_DATA) ?: ""
    }

    override fun setDeviceId(deviceId: String) {
        preferencesManager.setValue(KEY_DEVICE_ID, deviceId)
    }

    override fun getDeviceId(): String {
        return preferencesManager.getString(KEY_DEVICE_ID) ?: ""
    }

    override fun setUsername(username: String) {
        preferencesManager.setValue(KEY_USERNAME, username)
    }

    override fun getUsername(): String {
        return preferencesManager.getString(KEY_USERNAME) ?: ""
    }

    override fun setTerms(checked: Boolean) {
        preferencesManager.setValue(KEY_TERMS, checked)
    }

    override fun getTerms(): Boolean {
        return preferencesManager.getBoolean(KEY_TERMS)
    }

    override fun setFeatures(json: String) {
        preferencesManager.setValue(KEY_FEATURES, json)
    }

    override fun getFeatures(): String {
        return preferencesManager.getString(KEY_FEATURES) ?: "[]"
    }

    companion object {
        const val KEY_TOKEN_WS_DATA = "${PreferencesManager.NAME_FILE}_KEY_TOKEN_WS_DATA"
        const val KEY_TOKEN_BACKEND_DATA = "${PreferencesManager.NAME_FILE}_KEY_TOKEN_BACKEND_DATA"
        const val KEY_TOKEN_SOFYDOC_DATA = "${PreferencesManager.NAME_FILE}_KEY_TOKEN_SOFYDOC_DATA"
        const val KEY_DEVICE_ID = "${PreferencesManager.NAME_FILE}_KEY_DEVICE_ID"
        const val KEY_USERNAME = "${PreferencesManager.NAME_FILE}_KEY_USERNAME"
        const val KEY_TERMS = "${PreferencesManager.NAME_FILE}_KEY_TERMS"
        const val KEY_FEATURES = "${PreferencesManager.NAME_FILE}_KEY_FEATURES"
    }
}

interface IPreferenceApp {
    fun setTokenWS(token: String)
    fun getTokenWS(): String
    fun setTokenBackend(token: String)
    fun getTokenBackend(): String
    fun setTokenSofyDoc(token: String)
    fun getTokenSofyDoc(): String
    fun setDeviceId(deviceId: String)
    fun getDeviceId(): String
    fun setUsername(username: String)
    fun getUsername(): String
    fun setTerms(checked: Boolean)
    fun getTerms(): Boolean
    fun setFeatures(json: String)
    fun getFeatures(): String
}