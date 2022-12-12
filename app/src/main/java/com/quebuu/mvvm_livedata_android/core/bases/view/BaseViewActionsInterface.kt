package com.quebuu.mvvm_livedata_android.core.bases.view

import com.quebuu.mvvm_livedata_android.core.bases.viewmodel.Event

/**
 * Interface for basic actions for View
 */
interface BaseViewActionsInterface {

    fun onReadyData()
    fun onReadyView() {}
    fun bindObserversToLiveData() {}
    open fun retryCallHttp() {}

    //region Progress view
    fun showProgressView(event: Event<Unit>) {}
    fun hideProgressView(event: Event<Unit>) {}
    //endregion

    //region Internet Connection Error
    fun showErrorInternetConnection(event: Event<Unit>) {}
    //endregion

    //region message error
    fun showGenericError(event: Event<Unit>) {}
    fun showErrorApi(event: Event<String>) {}
    fun showUnauthorized(event: Event<Unit>) {}
    //endregion

}
