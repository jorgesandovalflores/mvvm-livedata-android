package com.quebuu.mvvm_livedata_android.features.auth.recovery

import androidx.fragment.app.FragmentActivity

class FeatureRecoveryNavigation : IFeatureRecoveryNavigation {

    override fun goToBack(activity: FragmentActivity) {
        activity.finish()
    }

}

interface IFeatureRecoveryNavigation {
    fun goToBack(activity: FragmentActivity)
}