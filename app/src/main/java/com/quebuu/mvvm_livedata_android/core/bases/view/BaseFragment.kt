package com.quebuu.mvvm_livedata_android.core.bases.view

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import org.koin.android.ext.android.inject
import mvvm_livedata_android.R
import com.quebuu.mvvm_livedata_android.common.components.dialog.ComponentDialog
import com.quebuu.mvvm_livedata_android.common.components.dialog.ComponentDialogModel
import com.quebuu.mvvm_livedata_android.core.bases.toGone
import com.quebuu.mvvm_livedata_android.core.bases.toVisible
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BaseViewModel
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.Event
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.observe
import com.quebuu.mvvm_livedata_android.core.repository.preferences.IPreferenceApp
import com.quebuu.mvvm_livedata_android.core.repository.preferences.IPreferenceUser
import com.quebuu.mvvm_livedata_android.features.splash.view.FeatureSplashViewActivity
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<T : ViewBinding> : Fragment(), BaseViewActionsInterface {

    lateinit var bindingView: T

    private lateinit var TAG_SCREEN: String

    //Generics views
    private lateinit var progressView: View
    private var mLastClickTime: Long = 0

    private val preferenceApp: IPreferenceApp by inject()
    private val preferenceUser: IPreferenceUser by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onReadyData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val type = javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[0] as Class<*>
        val method = clazz.getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        bindingView = method.invoke(null, layoutInflater, container, false) as T
        TAG_SCREEN = "[" + javaClass.simpleName + "]"
        Log.i("SCREEN", "*********************")
        Log.i("SCREEN", TAG_SCREEN)
        Log.i("SCREEN", "*********************")

        return bindingView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGenericsViews()
        onReadyView()
        bindObserversToLiveData()
    }

    override fun onResume() {
        super.onResume()
    }

    abstract fun getViewModel(): BaseViewModel

    override fun bindObserversToLiveData() {
        observe(getViewModel().bindingDelegate.showProgressView, this::showProgressView)
        observe(getViewModel().bindingDelegate.hideProgressView, this::hideProgressView)
        observe(
            getViewModel().bindingDelegate.showErrorInternetConnection,
            this::showErrorInternetConnection
        )
        observe(getViewModel().bindingDelegate.showGenericError, this::showGenericError)
        observe(getViewModel().bindingDelegate.showErrorApi, this::showErrorApi)
        observe(getViewModel().bindingDelegate.showUnauthorized, this::showUnauthorized)
    }

    open fun <T : Parcelable> getViewInputForActivity(): T? {
        if (requireActivity().intent != null &&
            requireActivity().intent.getParcelableExtra<T>(ARGS_KEY) != null) {
            return (requireActivity().intent.getParcelableExtra<T>(ARGS_KEY) as T)
        }
        return null
    }

    private fun initGenericsViews() {
        (view as? ViewGroup)?.let { viewGroup ->
            initProgressView(viewGroup)
        }
    }

    private fun initProgressView(viewGroupRoot: ViewGroup) {
        progressView = View.inflate(context, R.layout.core_view_custom_loading, viewGroupRoot)
            .findViewById(R.id.v_progress)
        progressView.toGone()
    }


    protected fun isActionEnable(): Boolean {
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return false
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        return true
    }

    override fun showProgressView(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                progressView.toVisible()
            }
        }
    }

    override fun hideProgressView(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                progressView.toGone()
            }
        }
    }

    override fun showErrorInternetConnection(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                val componentDialog = ComponentDialog.newInstance(
                    model = ComponentDialogModel(
                        icon = R.drawable.component_dialog_ic_not_wifi,
                        title = getString(R.string.component_dialog_not_wifi_title),
                        message = getString(R.string.component_dialog_not_wifi_body)
                    )
                )
                componentDialog.show(requireActivity().supportFragmentManager, ComponentDialog.TAG)
            }
        }
    }

    override fun showGenericError(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                val componentDialog = ComponentDialog.newInstance(
                    model = ComponentDialogModel(
                        title = getString(R.string.component_dialog_generic_title),
                        message = getString(R.string.component_dialog_generic_body)
                    )
                )
                componentDialog.show(requireActivity().supportFragmentManager, ComponentDialog.TAG)
            }
        }
    }

    override fun showErrorApi(event: Event<String>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                val componentDialog = ComponentDialog.newInstance(
                    model = ComponentDialogModel(
                        title = getString(R.string.component_dialog_api_error_title),
                        message = it
                    )
                )
                componentDialog.show(requireActivity().supportFragmentManager, ComponentDialog.TAG)
            }
        }
    }

    override fun showUnauthorized(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                val componentDialog = ComponentDialog.newInstance(
                    model = ComponentDialogModel(
                        title = getString(R.string.component_dialog_unauthorized_title),
                        message = getString(R.string.component_dialog_unauthorized_body),
                        buttonPrimaryText = R.string.component_dialog_unauthorized_button_primary
                    ),
                    buttonPrimaryClick = {
                        preferenceUser.setUser("")
                        preferenceUser.setProfile("")
                        preferenceUser.setImage("")
                        preferenceApp.setTokenWS("")
                        preferenceApp.setTokenBackend("")
                        preferenceApp.setTokenSofyDoc("")
                        it.dismiss()

                        requireActivity().finishAffinity()
                        requireActivity().startActivity(Intent(requireActivity(), FeatureSplashViewActivity::class.java))
                    }
                )
                componentDialog.show(requireActivity().supportFragmentManager, ComponentDialog.TAG)
            }
        }
    }

    companion object {
        const val ARGS_KEY = "inputViewData"
    }

}
