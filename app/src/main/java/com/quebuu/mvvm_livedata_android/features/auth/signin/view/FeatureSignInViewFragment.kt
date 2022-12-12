package com.quebuu.mvvm_livedata_android.features.auth.signin.view

import org.koin.androidx.viewmodel.ext.android.viewModel
import mvvm_livedata_android.databinding.FeatureSignInViewFragmentBinding
import com.quebuu.mvvm_livedata_android.core.bases.view.BaseFragment
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BaseViewModel
import androidx.navigation.fragment.navArgs
import org.koin.android.ext.android.inject
import mvvm_livedata_android.R
import com.quebuu.mvvm_livedata_android.common.components.dialog.ComponentDialogModel
import com.quebuu.mvvm_livedata_android.common.util.addTextChangedListener
import com.quebuu.mvvm_livedata_android.core.bases.toGone
import com.quebuu.mvvm_livedata_android.core.bases.toVisible
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.Event
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.observe
import com.quebuu.mvvm_livedata_android.features.auth.recovery.view.FeatureRecoveryViewInput
import com.quebuu.mvvm_livedata_android.features.auth.signin.IFeatureSignInNavigation
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.entity.FeatureSignInEntityRequest
import com.quebuu.mvvm_livedata_android.features.auth.signin.viewmodel.FeatureSignInViewModel

class FeatureSignInViewFragment : BaseFragment<FeatureSignInViewFragmentBinding>(),
    IFeatureSignInView {

    private val featureSignInViewModel: FeatureSignInViewModel by viewModel()
    private val viewInputArgument: FeatureSignInViewFragmentArgs by navArgs()
    private val featureSignInNavigation: IFeatureSignInNavigation by inject()

    // override methods
    override fun getViewModel(): BaseViewModel = featureSignInViewModel

    override fun bindObserversToLiveData() {
        super.bindObserversToLiveData()
        observe(featureSignInViewModel.bindingDelegate.showUsername, this::showUsername)
        observe(featureSignInViewModel.bindingDelegate.showTerms, this::showTerms)
        observe(featureSignInViewModel.bindingDelegate.hideTerms, this::hideTerms)
        observe(featureSignInViewModel.bindingDelegate.validateUsername, this::validateUsername)
        observe(featureSignInViewModel.bindingDelegate.validatePassword, this::validatePassword)
        observe(featureSignInViewModel.bindingDelegate.successSignIn, this::successSignIn)
        observe(
            featureSignInViewModel.bindingDelegate.showAccessProfileNotPermission,
            this::showAccessProfileNotPermission
        )
    }

    override fun onReadyData() {

    }

    override fun onReadyView() {
        //Initialize your ui here
        initEventInputs()
        initOnClickListener()

        featureSignInViewModel.callGetUsername()
        featureSignInViewModel.callGetTerms()
    }

    // ui methods
    private fun initOnClickListener() {
        bindingView.btnLogin.setOnClickListener {
            featureSignInViewModel.callSignIn(
                args = FeatureSignInEntityRequest(
                    username = bindingView.edUsername.getText()/*.toBase64()*/,
                    password = bindingView.edPassword.getText()/*.toBase64()*/
                ),
                remember = bindingView.chbxRemember.isChecked
            )
        }

        bindingView.lnkRecovery.setOnClickListener {
            featureSignInNavigation.goToRecovery(
                activity = requireActivity(),
                args = FeatureRecoveryViewInput(bindingView.edUsername.getText())
            )
        }

        bindingView.lnkTerms.setOnClickListener {
            featureSignInNavigation.goToTerms(
                requireActivity(),
                getString(R.string.feature_sign_in_url_terms)
            )
        }
    }

    private fun initEventInputs() {
        bindingView.edUsername.addTextChangedListener {
            featureSignInViewModel.presenterDelegate.validateUsername(true)
            featureSignInViewModel.presenterDelegate.validatePassword(false)
        }
        bindingView.edPassword.addTextChangedListener {
            featureSignInViewModel.presenterDelegate.validateUsername(false)
            featureSignInViewModel.presenterDelegate.validatePassword(true)
        }
    }

    // implements methods
    override fun showUsername(event: Event<String>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                bindingView.edUsername.setText(it)
                bindingView.chbxRemember.isChecked = true
            }
        }
    }

    override fun showTerms(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                bindingView.lnkTerms.toVisible()
            }
        }
    }

    override fun hideTerms(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                bindingView.lnkTerms.toGone()
            }
        }
    }

    override fun validateUsername(event: Event<Boolean>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                validateUsername(it)
            }
        }
    }

    override fun validatePassword(event: Event<Boolean>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                validatePassword(it)
            }
        }
    }

    override fun successSignIn(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                featureSignInNavigation.goToHome(requireActivity())
            }
        }
    }

    override fun showAccessProfileNotPermission(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                bindingView.chbxRemember.isChecked = false
                featureSignInNavigation.goToDialog(
                    activity = requireActivity(),
                    model = ComponentDialogModel(
                        title = getString(R.string.feature_sign_in_validate_profile_popup_title),
                        message = getString(R.string.feature_sign_in_validate_profile_popup_message)
                    )
                )
            }
        }
    }

    override fun showUnauthorized(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                featureSignInNavigation.goToDialog(
                    activity = requireActivity(),
                    model = ComponentDialogModel(
                        title = getString(R.string.feature_sign_in_validate_profile_popup_title),
                        message = getString(R.string.feature_sign_in_validate_profile_popup_message_other)
                    )
                )
            }
        }
    }

    // private methods
    private fun validateUsername(showError: Boolean): Boolean {
        return if (bindingView.edUsername.getText().isNullOrEmpty() ||
            !validateFormatUsername() ||
            bindingView.edUsername.getText().length < 10
        ) {
            if (showError) bindingView.edUsername.showError(R.string.feature_sign_in_validate_username)
            false
        } else {
            bindingView.edUsername.hideError()
            true
        }
    }

    private fun validateFormatUsername(): Boolean {
        return bindingView.edUsername.getText().isNotEmpty()
    }

    private fun validatePassword(showError: Boolean): Boolean {
        return if (bindingView.edPassword.getText().isNullOrEmpty() ||
            bindingView.edPassword.getText().length < 8
        ) {
            if (showError) bindingView.edPassword.showError(R.string.feature_sign_in_validate_password)
            false
        } else {
            bindingView.edPassword.hideError()
            bindingView.btnLogin.isEnabled =
                (!bindingView.edUsername.isError() && !bindingView.edPassword.isError())
            true
        }
    }

}

interface IFeatureSignInView {
    fun showUsername(event: Event<String>)
    fun showTerms(event: Event<Unit>)
    fun hideTerms(event: Event<Unit>)
    fun validateUsername(event: Event<Boolean>)
    fun validatePassword(event: Event<Boolean>)
    fun successSignIn(event: Event<Unit>)
    fun showAccessProfileNotPermission(event: Event<Unit>)
}