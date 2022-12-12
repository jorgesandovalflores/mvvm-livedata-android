package com.quebuu.mvvm_livedata_android.common.module

import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import com.quebuu.mvvm_livedata_android.common.datasource.repository.CommonRepository
import com.quebuu.mvvm_livedata_android.common.datasource.repository.ICommonRepository
import com.quebuu.mvvm_livedata_android.common.datasource.service.ICommonService
import com.quebuu.mvvm_livedata_android.common.usecase.GetCareersUseCase
import com.quebuu.mvvm_livedata_android.common.usecase.GetFeaturesUseCase
import com.quebuu.mvvm_livedata_android.common.usecase.SignInSofyDocUseCase
import com.quebuu.mvvm_livedata_android.core.repository.api.RETROFIT_API_JSON_BACKEND
import com.quebuu.mvvm_livedata_android.core.repository.api.RETROFIT_API_JSON_SOFYDOC
import com.quebuu.mvvm_livedata_android.core.repository.api.RETROFIT_API_JSON_WS
import retrofit2.Retrofit

val commonModule: Module = module {

    //Inject repository
    single<ICommonRepository> {
        CommonRepository(
            serviceWS = get(named("JSON_WS")),
            serviceBackend = get(named("JSON_BACKEND")),
            serviceSofyDoc = get(named("JSON_SOFYDOC"))
        )
    }

    //Inject useCase
    single { providerGetCareersUseCase(get()) }
    single { providerGetFeaturesUseCase(get()) }
    single { providerSignInSofyDocUseCase(get()) }

    single(named("JSON_WS")) { providerICommonService(get(named(RETROFIT_API_JSON_WS))) }
    single(named("JSON_BACKEND")) { providerICommonService(get(named(RETROFIT_API_JSON_BACKEND))) }
    single(named("JSON_SOFYDOC")) { providerICommonService(get(named(RETROFIT_API_JSON_SOFYDOC))) }
}

fun providerGetCareersUseCase(commonRepository: ICommonRepository): GetCareersUseCase {
    return GetCareersUseCase(commonRepository)
}

fun providerGetFeaturesUseCase(commonRepository: ICommonRepository): GetFeaturesUseCase {
    return GetFeaturesUseCase(commonRepository)
}

fun providerSignInSofyDocUseCase(commonRepository: ICommonRepository): SignInSofyDocUseCase {
    return SignInSofyDocUseCase(commonRepository)
}

fun providerICommonService(retrofit: Retrofit): ICommonService {
    return retrofit.create(ICommonService::class.java)
}