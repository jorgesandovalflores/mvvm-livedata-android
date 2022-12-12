package com.quebuu.mvvm_livedata_android.features.auth.recovery.module

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.quebuu.mvvm_livedata_android.core.repository.api.RETROFIT_API_JSON_WS
import com.quebuu.mvvm_livedata_android.features.auth.recovery.FeatureRecoveryNavigation
import com.quebuu.mvvm_livedata_android.features.auth.recovery.IFeatureRecoveryNavigation

import com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.repository.IFeatureRecoveryRepository
import com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.repository.FeatureRecoveryRepository
import com.quebuu.mvvm_livedata_android.features.auth.recovery.datasource.service.IFeatureRecoveryService
import com.quebuu.mvvm_livedata_android.features.auth.recovery.usecase.FeatureRecoveryUseCase
import com.quebuu.mvvm_livedata_android.features.auth.recovery.viewmodel.FeatureRecoveryBindingDelegate
import com.quebuu.mvvm_livedata_android.features.auth.recovery.viewmodel.FeatureRecoveryViewModel
import retrofit2.Retrofit

val featureRecoveryModule: Module = module {

    //Inject viewModel
    viewModel { FeatureRecoveryViewModel(featureRecoveryUseCase = get(), bindingDelegate = get()) }
    factory { providerFeatureRecoveryBindingDelegate() }

    //Inject repository
    single<IFeatureRecoveryRepository> { FeatureRecoveryRepository(get(named("JSON"))) }

    //Inject useCase
    single { providerFeatureRecoveryUseCase(get()) }

    //Inject service
    single(named("JSON")) { providerFeatureRecoveryService(get(named(RETROFIT_API_JSON_WS))) }

    //Inject navigation
    single { providerFeatureRecoveryNavigation() }
}

fun providerFeatureRecoveryBindingDelegate(): FeatureRecoveryBindingDelegate {
    return FeatureRecoveryBindingDelegate()
}

fun providerFeatureRecoveryUseCase(featureRecoveryRepository: IFeatureRecoveryRepository): FeatureRecoveryUseCase {
    return FeatureRecoveryUseCase(featureRecoveryRepository)
}

fun providerFeatureRecoveryService(retrofit: Retrofit): IFeatureRecoveryService {
    return retrofit.create(IFeatureRecoveryService::class.java)
}

fun providerFeatureRecoveryNavigation(): IFeatureRecoveryNavigation {
    return FeatureRecoveryNavigation()
}
        