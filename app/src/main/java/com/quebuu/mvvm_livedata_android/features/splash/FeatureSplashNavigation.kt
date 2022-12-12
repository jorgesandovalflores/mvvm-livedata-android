package com.quebuu.mvvm_livedata_android.features.splash

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.quebuu.mvvm_livedata_android.features.auth.signin.view.FeatureSignInViewActivity

class FeatureSplashNavigation : IFeatureSplashNavigation {

    override fun goToHome(activity: FragmentActivity) {
        activity.finishAffinity()
    }

    override fun goToSignIn(activity: FragmentActivity) {
        activity.finishAffinity()
        activity.startActivity(Intent(activity, FeatureSignInViewActivity::class.java))
    }

}

interface IFeatureSplashNavigation {
    fun goToHome(activity: FragmentActivity)
    fun goToSignIn(activity: FragmentActivity)
}