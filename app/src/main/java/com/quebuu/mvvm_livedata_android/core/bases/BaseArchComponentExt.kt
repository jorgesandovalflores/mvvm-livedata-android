package com.quebuu.mvvm_livedata_android.core.bases

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.os.Parcelable
import android.util.Base64
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import java.io.Serializable
import java.nio.charset.StandardCharsets

fun View.toVisible() {
    this.visibility = View.VISIBLE
}

fun View.toGone() {
    this.visibility = View.GONE
}

fun View.toInvisible() {
    this.visibility = View.INVISIBLE
}

fun String.clearAmount(): String {
    return this.replace("[S/ ,.]".toRegex(), "")
}

fun String.toBase64(): String {
    val encodedBytes: ByteArray = Base64.encode(this.encodeToByteArray(), Base64.DEFAULT)
    return String(encodedBytes, StandardCharsets.UTF_8).trim()
}

fun View.focusAndShowKeyboard() {
    /**
     * This is to be called when the window already has focus.
     */
    fun View.showTheKeyboardNow() {
        if (isFocused) {
            post {
                // We still post the call, just in case we are being notified of the windows focus
                // but InputMethodManager didn't get properly setup yet.
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }
    requestFocus()
    if (hasWindowFocus()) {
        // No need to wait for the window to get focus.
        showTheKeyboardNow()
    } else {
        // We need to wait until the window gets focus.
        viewTreeObserver.addOnWindowFocusChangeListener(
            object : ViewTreeObserver.OnWindowFocusChangeListener {
                override fun onWindowFocusChanged(hasFocus: Boolean) {
                    // This notification will arrive just before the InputMethodManager gets set up.
                    if (hasFocus) {
                        this@focusAndShowKeyboard.showTheKeyboardNow()
                        // It’s very important to remove this listener once we are done.
                        viewTreeObserver.removeOnWindowFocusChangeListener(this)
                    }
                }
            })
    }
}

fun <V> Map<String, V>.toBundle(bundle: Bundle = Bundle()): Bundle = bundle.apply {
    forEach {
        val k = it.key
        val v = it.value
        when (v) {
            is IBinder -> putBinder(k, v)
            is Bundle -> putBundle(k, v)
            is Byte -> putByte(k, v)
            is ByteArray -> putByteArray(k, v)
            is Char -> putChar(k, v)
            is CharArray -> putCharArray(k, v)
            is CharSequence -> putCharSequence(k, v)
            is Float -> putFloat(k, v)
            is FloatArray -> putFloatArray(k, v)
            is Parcelable -> putParcelable(k, v)
            is Serializable -> putSerializable(k, v)
            is Short -> putShort(k, v)
            is ShortArray -> putShortArray(k, v)
            else -> throw IllegalArgumentException("$v is of a type that is not currently supported")
        }
    }
}

fun Dialog.setTransparentBackground() {
    setOnShowListener {
        val bottomSheet = findViewById<View?>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.setBackgroundResource(android.R.color.transparent)
    }
}

@Suppress("BlockingMethodInNonBlockingContext")
suspend fun ResponseBody.stringSuspending() =
        withContext(Dispatchers.IO) { string() }