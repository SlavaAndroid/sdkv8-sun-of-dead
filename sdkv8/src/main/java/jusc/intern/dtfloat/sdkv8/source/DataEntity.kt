package jusc.intern.dtfloat.sdkv8.source

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import jusc.intern.dtfloat.sdkv8.response.RespData

@Entity(tableName = "resp_data")
data class DataEntity(
    @PrimaryKey val id: Long = 0,
    var savedData: Boolean = false,
    @Embedded var respData: RespData
)