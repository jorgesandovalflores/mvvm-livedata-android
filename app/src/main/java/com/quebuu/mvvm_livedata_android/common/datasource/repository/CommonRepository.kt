package com.quebuu.mvvm_livedata_android.common.datasource.repository

import com.quebuu.mvvm_livedata_android.common.datasource.entity.CareerEntityRequest
import com.quebuu.mvvm_livedata_android.common.datasource.service.ICommonService
import com.quebuu.mvvm_livedata_android.common.usecase.*

class CommonRepository(private val serviceWS: ICommonService, private val serviceBackend: ICommonService, private val serviceSofyDoc: ICommonService) : ICommonRepository {

    override suspend fun callGetCareers(request: CareerEntityRequest): List<CareerModel> {
        return serviceWS.getCareers(
            institution = request.institution,
            username = request.username,
            profile = request.profile
        ).toUseCaseModel()
    }

    override suspend fun callGetFeatures(): List<FeatureModel> {
        return serviceBackend.getFeatures(institution = "WIENER").toCaseModel()
    }

    override suspend fun callSignInSofyDoc(base64: String): CareerSofyDocDataModel {
        return serviceSofyDoc.signInSofyDoc(tokenWiener = base64).toMod()
    }

}