package com.quebuu.mvvm_livedata_android.common.components.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import mvvm_livedata_android.R
import com.quebuu.mvvm_livedata_android.core.bases.toGone
import com.quebuu.mvvm_livedata_android.core.bases.toVisible

class ComponentDialog() : DialogFragment() {

    private var model: ComponentDialogModel? = null
    private var buttonPrimaryClick: ((ComponentDialog) -> Unit)? = null
    private var buttonSecondaryClick: ((ComponentDialog) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.component_dialog, container, false)
    }

    companion object {
        const val TAG = "ComponentDialog"
        fun newInstance(
            model: ComponentDialogModel,
            buttonPrimaryClick: ((ComponentDialog) -> Unit)? = null,
            buttonSecondaryClick: ((ComponentDialog) -> Unit)? = null
        ): ComponentDialog {
            val fragment = ComponentDialog()
            fragment.model = model
            fragment.buttonPrimaryClick = buttonPrimaryClick
            fragment.buttonSecondaryClick = buttonSecondaryClick
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model?.let {
            view.findViewById<ImageView>(R.id.ivClose).setOnClickListener { dismiss() }
            view.findViewById<ImageView>(R.id.ivError).setImageResource(it.icon)
            view.findViewById<TextView>(R.id.tvTitle).text = it.title
            view.findViewById<TextView>(R.id.tvMessage).text = it.message

            if (it.message.isNullOrEmpty()) {
                view.findViewById<TextView>(R.id.tvMessage).toGone()
            }

            it.buttonPrimaryText?.let { buttonPrimary ->
                view.findViewById<Button>(R.id.btnPrimary).setText(buttonPrimary)
                view.findViewById<Button>(R.id.btnPrimary).toVisible()
                view.findViewById<Button>(R.id.btnPrimary).setOnClickListener {
                    buttonPrimaryClick?.invoke(this)
                }
            }

            it.buttonSecondaryText?.let { buttonSecondary ->
                view.findViewById<Button>(R.id.btnSecondary).setText(buttonSecondary)
                view.findViewById<Button>(R.id.btnSecondary).toVisible()
                view.findViewById<Button>(R.id.btnSecondary).setOnClickListener {
                    buttonSecondaryClick?.invoke(this)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

}

data class ComponentDialogModel(
    @DrawableRes val icon: Int = R.drawable.component_dialog_ic_alert,
    val title: String,
    val message: String,
    @StringRes val buttonPrimaryText: Int? = null,
    @StringRes val buttonSecondaryText: Int? = null
)