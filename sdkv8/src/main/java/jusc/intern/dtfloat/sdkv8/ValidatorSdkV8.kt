package jusc.intern.dtfloat.sdkv8

import android.content.Context
import jusc.intern.dtfloat.sdkv8.installer.MainJob
import jusc.intern.dtfloat.sdkv8.repository.local.LocalRepoImpl
import jusc.intern.dtfloat.sdkv8.repository.remote.RemoteRepoImpl
import jusc.intern.dtfloat.sdkv8.response.ValidatorRespV8
import jusc.intern.dtfloat.sdkv8.source.DataEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Suppress("unused")
class ValidatorSdkV8(
    context: Context,
    gadId: String
) {

    private val remoteRepo = RemoteRepoImpl()
    private val localRepo = LocalRepoImpl(context)
    private val mainJob = MainJob(context = context, gadId = gadId, remoteRepo = remoteRepo)

    @Suppress("unused")
    fun start(): Flow<ValidatorRespV8> = flow {

        val cash = localRepo.getData()

        if (cash != null && cash.savedData) {
            emit(ValidatorRespV8.Repeat(cash.respData))
        } else {
            when(val response = mainJob.requestRemote()) {
                is ValidatorRespV8.Error -> {
                    emit(response)
                }
                is ValidatorRespV8.Success -> {
                    val data = response.data
                    localRepo.saveData(
                        DataEntity(
                            savedData = true,
                            respData = data
                        )
                    )
                    emit(ValidatorRespV8.Success(data))
                }
                else -> {}
            }
        }
    }
}