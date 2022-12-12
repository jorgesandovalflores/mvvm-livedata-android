package com.quebuu.mvvm_livedata_android.features.auth.signin.module

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.quebuu.mvvm_livedata_android.core.repository.api.RETROFIT_API_JSON_BACKEND
import com.quebuu.mvvm_livedata_android.core.repository.api.RETROFIT_API_JSON_WS
import com.quebuu.mvvm_livedata_android.features.auth.signin.FeatureSignInNavigation
import com.quebuu.mvvm_livedata_android.features.auth.signin.IFeatureSignInNavigation

import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.repository.IFeatureSignInRepository
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.repository.FeatureSignInRepository
import com.quebuu.mvvm_livedata_android.features.auth.signin.datasource.service.IFeatureSignInService
import com.quebuu.mvvm_livedata_android.features.auth.signin.usecase.FeatureSignInGetInformationUseCase
import com.quebuu.mvvm_livedata_android.features.auth.signin.usecase.FeatureSignInGetProfileUseCase
import com.quebuu.mvvm_livedata_android.features.auth.signin.usecase.FeatureSignInUseCase
import com.quebuu.mvvm_livedata_android.features.auth.signin.viewmodel.FeatureSignInBindingDelegate
import com.quebuu.mvvm_livedata_android.features.auth.signin.viewmodel.FeatureSignInViewModel
import retrofit2.Retrofit

val featureSignInModule: Module = module {

    //Inject viewModel
    viewModel {
        FeatureSignInViewModel(
            featureSignInUseCase = get(),
            featureSignInGetProfileUseCase = get(),
            featureSignInGetInformationUseCase = get(),
            getFeaturesUseCase = get(),
            signInSofyDocUseCase = get(),
            preferenceUser = get(),
            preferencesApp = get(),
            bindingDelegate = get()
        )
    }
    factory { providerFeatureSignInBindingDelegate() }

    //Inject repository
    single<IFeatureSignInRepository> {
        FeatureSignInRepository(
            serviceJSONBACKEND = get(named("JSON_BACKEND")),
            serviceJSONWS = get(named("JSON_WS"))
        )
    }

    //Inject useCase
    single { providerFeatureSignInUseCase(get()) }
    single { providerFeatureSignInGetStatusUseCase(get()) }
    single { provideFeatureSignInGetInformationUseCase(get()) }

    //Inject service
    single(named("JSON_BACKEND")) { providerFeatureSignInService(get(named(RETROFIT_API_JSON_BACKEND))) }
    single(named("JSON_WS")) { providerFeatureSignInService(get(named(RETROFIT_API_JSON_WS))) }

    //Inject navigation
    single { providerFeatureSignInNavigation() }
}

fun providerFeatureSignInBindingDelegate(): FeatureSignInBindingDelegate {
    return FeatureSignInBindingDelegate()
}

fun providerFeatureSignInUseCase(featureSignInRepository: IFeatureSignInRepository): FeatureSignInUseCase {
    return FeatureSignInUseCase(featureSignInRepository)
}

fun providerFeatureSignInGetStatusUseCase(featureSignInRepository: IFeatureSignInRepository): FeatureSignInGetProfileUseCase {
    return FeatureSignInGetProfileUseCase(featureSignInRepository)
}

fun provideFeatureSignInGetInformationUseCase(featureSignInRepository: IFeatureSignInRepository): FeatureSignInGetInformationUseCase {
    return FeatureSignInGetInformationUseCase(featureSignInRepository)
}

fun providerFeatureSignInService(retrofit: Retrofit): IFeatureSignInService {
    return retrofit.create(IFeatureSignInService::class.java)
}

fun providerFeatureSignInNavigation(): IFeatureSignInNavigation {
    return FeatureSignInNavigation()
}