package com.quebuu.mvvm_livedata_android.features.auth.recovery.view

import org.koin.androidx.viewmodel.ext.android.viewModel
import mvvm_livedata_android.databinding.FeatureRecoveryViewFragmentBinding
import com.quebuu.mvvm_livedata_android.core.bases.view.BaseFragment
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BaseViewModel
import org.koin.android.ext.android.inject
import mvvm_livedata_android.R
import com.quebuu.mvvm_livedata_android.common.util.addTextChangedListener
import com.quebuu.mvvm_livedata_android.common.util.addTextChangedListenerClear
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.Event
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.observe
import com.quebuu.mvvm_livedata_android.features.auth.recovery.IFeatureRecoveryNavigation
import com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.entity.FeatureRecoveryEntityRequest
import com.quebuu.mvvm_livedata_android.features.auth.recovery.viewmodel.FeatureRecoveryViewModel

class FeatureRecoveryViewFragment : BaseFragment<FeatureRecoveryViewFragmentBinding>(),
    IFeatureRecoveryViewFragment {

    private val featureRecoveryViewModel: FeatureRecoveryViewModel by viewModel()
    private val featureRecoveryNavigation: IFeatureRecoveryNavigation by inject()
    private var featureRecoveryViewInput: FeatureRecoveryViewInput? = null

    // override methods
    override fun getViewModel(): BaseViewModel = featureRecoveryViewModel

    override fun bindObserversToLiveData() {
        super.bindObserversToLiveData()
        observe(featureRecoveryViewModel.bindingDelegate.validateUsername, this::validateUsername)
        observe(featureRecoveryViewModel.bindingDelegate.showUsername, this::showUsername)
        observe(
            featureRecoveryViewModel.bindingDelegate.successRecoveryPassword,
            this::successRecoveryPassword
        )
    }

    override fun onReadyData() {
        featureRecoveryViewInput = getViewInputForActivity()
        if (featureRecoveryViewInput == null && arguments != null) {
            featureRecoveryViewInput =
                FeatureRecoveryViewFragmentArgs.fromBundle(requireArguments()).featureRecoveryViewInput
        }
    }

    override fun onReadyView() {
        //Initialize your ui here
        initToolbar()
        initData()
        initEventInputs()
        initClickEvent()
    }

    // support methods
    private fun initToolbar() {
        bindingView.toolbar.setNavigationOnClickListener {
            featureRecoveryNavigation.goToBack(
                requireActivity()
            )
        }
    }

    private fun initData() {
        featureRecoveryViewInput?.let {
            if (!it.username.isNullOrEmpty()) featureRecoveryViewModel.presenterDelegate.showUsername(
                it.username
            )
        }
    }

    private fun initEventInputs() {
        bindingView.edUsername.addTextChangedListener {
            featureRecoveryViewModel.presenterDelegate.validateUsername(true)
        }
    }

    private fun initClickEvent() {
        bindingView.btnSend.setOnClickListener {
            featureRecoveryViewModel.callRecoveryPassword(
                request = FeatureRecoveryEntityRequest(
                    username = bindingView.edUsername.getText()
                )
            )
        }
    }

    // implement methods
    override fun validateUsername(event: Event<Boolean>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                validateUsername(it)
            }
        }
    }

    override fun showUsername(event: Event<String>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                bindingView.edUsername.addTextChangedListenerClear()
                bindingView.edUsername.setText(it)
                featureRecoveryViewModel.presenterDelegate.validateUsername(true)
                initEventInputs()
            }
        }
    }

    override fun successRecoveryPassword(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                bindingView.tvText.text = getString(R.string.feature_recovery_text_two)
                bindingView.btnSend.text = getString(R.string.feature_recovery_button_resend)
            }
        }
    }

    // private methods
    private fun validateUsername(showError: Boolean): Boolean {
        return if (bindingView.edUsername.getText().isNullOrEmpty() ||
            !validateFormatUsername() ||
            bindingView.edUsername.getText().length < 10
        ) {
            if (showError) bindingView.edUsername.showError(R.string.feature_recovery_validate_username)
            bindingView.btnSend.isEnabled = false
            false
        } else {
            bindingView.edUsername.hideError()
            bindingView.btnSend.isEnabled = true
            true
        }
    }

    private fun validateFormatUsername(): Boolean {
        return bindingView.edUsername.getText().isNotEmpty()
    }

}

interface IFeatureRecoveryViewFragment {
    fun showUsername(event: Event<String>)
    fun validateUsername(event: Event<Boolean>)
    fun successRecoveryPassword(event: Event<Unit>)
}