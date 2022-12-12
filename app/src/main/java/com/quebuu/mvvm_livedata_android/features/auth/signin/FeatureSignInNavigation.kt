package com.quebuu.mvvm_livedata_android.features.auth.signin

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.FragmentActivity
import com.quebuu.mvvm_livedata_android.common.components.dialog.ComponentDialog
import com.quebuu.mvvm_livedata_android.common.components.dialog.ComponentDialogModel
import com.quebuu.mvvm_livedata_android.core.bases.view.BaseFragment
import com.quebuu.mvvm_livedata_android.features.auth.recovery.view.FeatureRecoveryViewActivity
import com.quebuu.mvvm_livedata_android.features.auth.recovery.view.FeatureRecoveryViewInput

class FeatureSignInNavigation : IFeatureSignInNavigation {

    override fun goToHome(activity: FragmentActivity) {
        activity.finishAffinity()

    }

    override fun goToDialog(activity: FragmentActivity, model: ComponentDialogModel) {
        val componentDialog = ComponentDialog.newInstance(model = model)
        componentDialog.show(activity.supportFragmentManager, ComponentDialog.TAG)
    }

    override fun goToRecovery(activity: FragmentActivity, args: FeatureRecoveryViewInput) {
        val intent = Intent(activity, FeatureRecoveryViewActivity::class.java)
        intent.putExtra(BaseFragment.ARGS_KEY, args)
        activity.startActivity(intent)
    }

    override fun goToTerms(activity: FragmentActivity, url: String) {
        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}

interface IFeatureSignInNavigation {
    fun goToHome(activity: FragmentActivity)
    fun goToDialog(activity: FragmentActivity, model: ComponentDialogModel)
    fun goToRecovery(activity: FragmentActivity, args: FeatureRecoveryViewInput)
    fun goToTerms(activity: FragmentActivity, url: String)
}