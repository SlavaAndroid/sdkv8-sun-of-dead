package jusc.intern.dtfloat.sdkv8.repository.remote

import jusc.intern.dtfloat.sdkv8.api.model.AppInfoData
import jusc.intern.dtfloat.sdkv8.api.model.InputData
import jusc.intern.dtfloat.sdkv8.api.model.RefData


interface RemoteRepo {

    suspend fun sendRefData(data: RefData): Resource<InputData>

    suspend fun sendAppInfo(data: AppInfoData): Resource<InputData>
}