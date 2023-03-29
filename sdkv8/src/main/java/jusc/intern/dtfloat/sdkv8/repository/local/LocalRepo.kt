package jusc.intern.dtfloat.sdkv8.repository.local

import jusc.intern.dtfloat.sdkv8.source.DataEntity

interface LocalRepo {

    suspend fun saveData(dataEntity: DataEntity)

    suspend fun getData(): DataEntity?
}