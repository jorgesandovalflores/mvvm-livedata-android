package com.quebuu.mvvm_livedata_android.common.components.textinput

import android.content.Context
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputEditText
import mvvm_livedata_android.R
import com.quebuu.mvvm_livedata_android.core.bases.toGone
import com.quebuu.mvvm_livedata_android.core.bases.toInvisible
import com.quebuu.mvvm_livedata_android.core.bases.toVisible


class ComponentTextInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val inputTitle: TextView
    private val inputEditText: TextInputEditText
    private val inputError: TextView
    private val iconPassword: ImageView
    private val iconError: ImageView
    private var isPassword: Boolean = false
    private var showPassword: Boolean = false

    init {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.component_text_input, this, true)
        inputTitle = view.findViewById(R.id.tv_input_title)
        inputEditText = view.findViewById(R.id.ed_input)
        inputError = view.findViewById(R.id.tv_input_error)
        iconError = view.findViewById(R.id.iv_icon_error)
        iconPassword = view.findViewById(R.id.iv_icon_password)
        iconPassword.setOnClickListener {
            if (showPassword) {
                hidePassword()
            } else {
                showPassword()
            }
            showPassword = !showPassword
        }

        context.theme.obtainStyledAttributes(attrs, R.styleable.ComponentTextInput, 0, 0).apply {
            try {
                setInputType(getInt(R.styleable.ComponentTextInput_inputType, 0))
                setImeOptions(getInt(R.styleable.ComponentTextInput_android_imeOptions, 0))
                setMaxLength(getInt(R.styleable.ComponentTextInput_android_maxLength, 0))
                getString(R.styleable.ComponentTextInput_android_hint)?.let { setHint(it) }
            } finally {
                recycle()
            }
        }
    }

    // public methods
    fun setInputType(type: TextInputType) {
        setInputType(type.value)
    }

    fun setMaxLength(length: Int) {
        inputEditText.filters = arrayOf<InputFilter>(LengthFilter(length))
    }

    fun setImeOptions(ime: TextInputIme) {
        setImeOptions(ime.value)
    }

    fun setText(text: String) {
        inputEditText.setText(text)
    }

    fun setText(@StringRes text: Int) {
        inputEditText.setText(text)
    }

    fun getText(): String {
        return inputEditText.text.toString()
    }

    fun showError(
        @StringRes resource: Int,
        @DrawableRes icon: Int = R.drawable.component_text_input_ic_alert
    ) {
        inputError.toVisible()
        inputError.setText(resource)
        inputEditText.setBackgroundResource(R.drawable.component_text_input_error)
        iconError.setImageResource(icon)
        iconError.toVisible()
        if (isPassword) {
            iconPassword.toGone()
        }
    }

    fun hideError() {
        inputError.toInvisible()
        iconError.toGone()
        inputEditText.setBackgroundResource(R.drawable.component_text_input_selector)
        if (isPassword) {
            iconPassword.toVisible()
        }
    }

    fun getInputEditText(): TextInputEditText {
        return inputEditText
    }

    fun isError(): Boolean {
        return inputError.visibility == View.VISIBLE
    }

    fun textClear() {
        inputEditText.text = null
    }

    // private methods
    private fun setInputType(type: Int) {
        inputEditText.inputType = type
        if (type == TextInputType.NUMBER_PASSWORD.value || type == TextInputType.TEXT_PASSWORD.value) {
            isPassword = true
            iconPassword.toVisible()
            hidePassword()
        }
    }

    private fun setImeOptions(ime: Int) {
        inputEditText.imeOptions = ime
    }

    private fun setHint(hint: String) {
        inputTitle.text = hint
    }

    private fun showPassword() {
        inputEditText.transformationMethod = (HideReturnsTransformationMethod.getInstance())
        iconPassword.setImageResource(R.drawable.component_text_input_ic_open_eye)
    }

    private fun hidePassword() {
        inputEditText.transformationMethod = (PasswordTransformationMethod.getInstance())
        iconPassword.setImageResource(R.drawable.component_text_input_ic_close_eye)
    }

    enum class TextInputType(val value: Int) {
        TEXT(InputType.TYPE_CLASS_TEXT),
        TEXT_ALL_CAPS(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS),
        TEXT_EMAIL(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS),
        TEXT_PASSWORD(InputType.TYPE_TEXT_VARIATION_PASSWORD),
        NUMBER(InputType.TYPE_CLASS_NUMBER),
        NUMBER_DECIMAL(InputType.TYPE_NUMBER_FLAG_DECIMAL),
        NUMBER_PASSWORD(InputType.TYPE_NUMBER_VARIATION_PASSWORD)
    }

    enum class TextInputIme(val value: Int) {
        DONE(EditorInfo.IME_ACTION_DONE),
        GO(EditorInfo.IME_ACTION_GO),
        NEXT(EditorInfo.IME_ACTION_NEXT),
        SEND(EditorInfo.IME_ACTION_SEND)
    }

}