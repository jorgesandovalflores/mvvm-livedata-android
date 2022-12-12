package com.quebuu.mvvm_livedata_android.features.splash.view

import org.koin.androidx.viewmodel.ext.android.viewModel
import mvvm_livedata_android.databinding.FeatureSplashViewFragmentBinding
import com.quebuu.mvvm_livedata_android.core.bases.view.BaseFragment
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.BaseViewModel
import androidx.navigation.fragment.navArgs
import org.koin.android.ext.android.inject
import com.quebuu.mvvm_livedata_android.core.bases.toGone
import com.quebuu.mvvm_livedata_android.core.bases.toVisible
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.Event
import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.observe
import com.quebuu.mvvm_livedata_android.features.splash.IFeatureSplashNavigation
import com.quebuu.mvvm_livedata_android.features.splash.viewmodel.FeatureSplashViewModel

class FeatureSplashViewFragment : BaseFragment<FeatureSplashViewFragmentBinding>(),
    IFeatureSplashView {

    private val featureSplashViewModel: FeatureSplashViewModel by viewModel()
    private val viewInputArgument: FeatureSplashViewFragmentArgs by navArgs()
    private val featureSplashNavigation: IFeatureSplashNavigation by inject()

    // override methods
    override fun getViewModel(): BaseViewModel = featureSplashViewModel

    override fun bindObserversToLiveData() {
        super.bindObserversToLiveData()
        observe(featureSplashViewModel.bindingDelegate.showCustomProgress, this::showCustomProgress)
        observe(featureSplashViewModel.bindingDelegate.hideCustomProgress, this::hideCustomProgress)
        observe(featureSplashViewModel.bindingDelegate.showHome, this::showHome)
        observe(featureSplashViewModel.bindingDelegate.showSignIn, this::showSignIn)
    }

    override fun onReadyData() {

    }

    override fun onReadyView() {
        //Initialize your ui here
        featureSplashViewModel.callParameters()
    }

    // ui methods

    // implements methods
    override fun showCustomProgress(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                bindingView.progress.toVisible()
            }
        }
    }

    override fun hideCustomProgress(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                bindingView.progress.toGone()
            }
        }
    }

    override fun showHome(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                featureSplashNavigation.goToHome(requireActivity())
            }
        }
    }

    override fun showSignIn(event: Event<Unit>) {
        event.getContentIfNotHandled().let {
            it?.apply {
                featureSplashNavigation.goToSignIn(requireActivity())
            }
        }
    }

}

interface IFeatureSplashView {
    fun showCustomProgress(event: Event<Unit>)
    fun hideCustomProgress(event: Event<Unit>)
    fun showHome(event: Event<Unit>)
    fun showSignIn(event: Event<Unit>)
}