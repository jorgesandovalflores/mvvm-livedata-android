package com.quebuu.mvvm_livedata_android.common.components.spinner

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mvvm_livedata_android.R

class ComponentSpinner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private var arrow: ImageView
    private var title: TextView
    private var hint: TextView
    private val layout: ConstraintLayout

    private var isShow: Boolean = false
    private var items: ArrayList<String> = arrayListOf()
    private var positionSelected: Int = -1
    private var onLister: OnListener? = null
    private var fragmentManager: FragmentManager? = null
    private var textHint: String? = ""
    private val TAG = "AgoraXBottomSheetDialog"

    private val onPositionSelectedLiveData = MutableLiveData<Boolean>()
    val onPositionSelected: LiveData<Boolean> get() = onPositionSelectedLiveData

    init {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.component_spinner, this, true)

        arrow = view.findViewById(R.id.ivArrow)
        title = view.findViewById(R.id.tvTitle)
        hint = view.findViewById(R.id.tvHint)
        layout = view.findViewById(R.id.lySpinner)

        layout.setOnClickListener {
            loadActive()
        }

        context.theme.obtainStyledAttributes(attrs, R.styleable.ComponentSpinner, 0, 0).apply {
            textHint = getString(R.styleable.ComponentSpinner_android_hint)
            if (!textHint.isNullOrEmpty()) {
                title.text = textHint
            }
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        layout.setOnClickListener(l)
    }

    fun setItems(items: ArrayList<String>, positionSelected: Int = -1) {
        this.items = items
        this.positionSelected = positionSelected

        if (positionSelected > -1) {
            hint.visibility = VISIBLE
            hint.text = textHint
            title.text = items[positionSelected]
        }
    }

    fun setPositionSelected(positionSelected: Int) {
        this.positionSelected = positionSelected
        hint.visibility = VISIBLE
        hint.text = textHint
        title.text = items[positionSelected]
        onPositionSelectedLiveData.postValue(true)
    }

    fun setValueString(value: String) {
        hint.visibility = VISIBLE
        hint.text = textHint
        title.text = value
    }

    fun getValueString(): String {
        return title.text.toString()
    }

    fun setOnListener(onLister: OnListener, fragmentManager: FragmentManager) {
        this.onLister = onLister
        this.fragmentManager = fragmentManager
    }

    fun getItemSelectedPosition(): Int {
        return this.positionSelected
    }

    fun getItemSelected(): String {
        return this.items[this.positionSelected]
    }

    fun getHintText(): String? {
        return textHint
    }

    fun isValid(): Boolean {
        return positionSelected > -1
    }

    fun clear() {
        positionSelected = -1
        hint.visibility = GONE
        title.text = textHint
        onPositionSelectedLiveData.postValue(false)
    }

    fun setActive(isActive: Boolean) {
        if (isActive) {
            layout.setOnClickListener { loadActive() }
        } else {
            layout.setOnClickListener(null)
        }
    }

    fun hasItems(): Boolean {
        return items.isNotEmpty()
    }

    private fun startRotatingImageUp(view: View) {
        val startRotateAnimation = AnimationUtils.loadAnimation(context, R.anim.component_spinner_rotate_up_animation)
        view.startAnimation(startRotateAnimation)
    }

    private fun startRotatingImageDown(view: View) {
        val startRotateAnimation = AnimationUtils.loadAnimation(context, R.anim.component_spinner_rotate_down_animation)
        view.startAnimation(startRotateAnimation)
    }

    interface OnListener {
        fun selectedItemSpinner(position: Int, value: String)
    }

    private fun loadActive() {
        if (!isShow && fragmentManager!=null) {
            startRotatingImageUp(arrow)
            ComponentSpinnerBottomSheetDialog.newInstance(items, positionSelected, { i: Int, s: String ->
                onLister?.let {
                    hint.visibility = VISIBLE
                    hint.text = textHint
                    title.text = s
                    positionSelected = i
                    onLister?.selectedItemSpinner(i, s)
                    onPositionSelectedLiveData.postValue(true)
                }
            }, {
                isShow = false
                startRotatingImageDown(arrow)
            }).show(fragmentManager!!, TAG)
            isShow = true
        }
    }
}