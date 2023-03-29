package jusc.intern.dtfloat.sdkv8.api

import jusc.intern.dtfloat.sdkv8.api.model.InputData
import jusc.intern.dtfloat.sdkv8.api.model.RefData
import jusc.intern.dtfloat.sdkv8.api.model.UserAgent
import retrofit2.Response
import retrofit2.http.*

interface SdkService {

    @POST("cks9adyv2x")
    suspend fun postRefData(@Body data: RefData): Response<InputData>

    @POST("cks9adyv2x")
    suspend fun postAppInfo(
        @Query("04sj9iod") sdkVersion: String,
        @Query("2njfvbn7") limitAdTracking: Int,
        @Header("e8jc19t") bundle: String,
        @Header("sxz1qb6bc") gadId: String,
        @Header("9tbuu8wk") appVersion: Long,
        @Header("rj1dc59") osVersion: String,
        @Header("rbsi1ou6") idType: Int,
        @Header("g6jm65r") timestamp: Float,
        @Body data: UserAgent
    ): Response<InputData>
}