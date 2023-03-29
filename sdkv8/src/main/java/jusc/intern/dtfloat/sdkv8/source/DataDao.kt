package jusc.intern.dtfloat.sdkv8.source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(dataEntity: DataEntity)

    @Query("SELECT * FROM resp_data LIMIT 1")
    suspend fun getData(): DataEntity?

}