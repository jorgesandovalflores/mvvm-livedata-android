package com.quebuu.mvvm_livedata_android.features.splash.module

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.quebuu.mvvm_livedata_android.core.repository.api.RETROFIT_API_JSON_WS
import com.quebuu.mvvm_livedata_android.features.splash.FeatureSplashNavigation
import com.quebuu.mvvm_livedata_android.features.splash.IFeatureSplashNavigation

import com.quebuu.mvvm_livedata_android.features.splash.datasource.repository.IFeatureSplashRepository
import com.quebuu.mvvm_livedata_android.features.splash.datasource.repository.FeatureSplashRepository
import com.quebuu.mvvm_livedata_android.features.splash.datasource.service.IFeatureSplashService
import com.quebuu.mvvm_livedata_android.features.splash.usecase.FeatureSplashUseCase
import com.quebuu.mvvm_livedata_android.features.splash.viewmodel.FeatureSplashBindingDelegate
import com.quebuu.mvvm_livedata_android.features.splash.viewmodel.FeatureSplashViewModel
import retrofit2.Retrofit

val featureSplashModule: Module = module {

    //Inject viewModel
    viewModel {
        FeatureSplashViewModel(
            getFeaturesUseCase = get(),
            preferenceApp = get(),
            bindingDelegate = get()
        )
    }
    factory { providerFeatureSplashBindingDelegate() }

    //Inject repository
    single<IFeatureSplashRepository> { FeatureSplashRepository(get()) }

    //Inject useCase
    single { providerFeatureSplashUseCase(get()) }

    //Inject service
    single { providerFeatureSplashService(get(named(RETROFIT_API_JSON_WS))) }

    //Inject navigation
    single { providerFeatureSplashNavigation() }
}

fun providerFeatureSplashBindingDelegate(): FeatureSplashBindingDelegate {
    return FeatureSplashBindingDelegate()
}

fun providerFeatureSplashUseCase(featureSplashRepository: IFeatureSplashRepository): FeatureSplashUseCase {
    return FeatureSplashUseCase(featureSplashRepository)
}

fun providerFeatureSplashService(retrofit: Retrofit): IFeatureSplashService {
    return retrofit.create(IFeatureSplashService::class.java)
}

fun providerFeatureSplashNavigation(): IFeatureSplashNavigation {
    return FeatureSplashNavigation()
}
        
        