package jusc.intern.dtfloat.sdkv8.repository.local

import android.content.Context
import jusc.intern.dtfloat.sdkv8.source.DataEntity
import jusc.intern.dtfloat.sdkv8.source.SdkDatabase

class LocalRepoImpl(context: Context) : LocalRepo {

    private val dataBase = SdkDatabase.getInstance(context)

    override suspend fun saveData(dataEntity: DataEntity) {
        dataBase.dataDao().insertData(dataEntity)
    }

    override suspend fun getData(): DataEntity? {
        return dataBase.dataDao().getData()
    }
}