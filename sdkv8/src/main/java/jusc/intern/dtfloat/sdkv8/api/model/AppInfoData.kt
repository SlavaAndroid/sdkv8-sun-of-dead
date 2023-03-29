package jusc.intern.dtfloat.sdkv8.api.model

data class AppInfoData(
    val sdkVersion: String,
    val limitAdTracking: Int,
    val bundle: String,
    val gadId: String,
    val appVersion: Long,
    val osVersion: String,
    val idType: Int,
    val timestamp: Float,
    val data: UserAgent
)
