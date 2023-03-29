package jusc.intern.dtfloat.sdkv8.repository.remote

import jusc.intern.dtfloat.sdkv8.api.ApiFactory
import jusc.intern.dtfloat.sdkv8.api.model.AppInfoData
import jusc.intern.dtfloat.sdkv8.api.model.InputData
import jusc.intern.dtfloat.sdkv8.api.model.RefData


class RemoteRepoImpl : BaseRepo(), RemoteRepo {

    private var remote = ApiFactory.sdkService
    override suspend fun sendRefData(data: RefData): Resource<InputData> {
        return safeApiCall<InputData> { remote.postRefData(data) }
    }

    override suspend fun sendAppInfo(data: AppInfoData): Resource<InputData> {
        return safeApiCall<InputData> { remote.postAppInfo(
            sdkVersion = data.sdkVersion,
            limitAdTracking = data.limitAdTracking,
            bundle = data.bundle,
            gadId = data.gadId,
            appVersion = data.appVersion,
            osVersion = data.osVersion,
            idType = data.idType,
            timestamp = data.timestamp,
            data = data.data
        ) }
    }
}