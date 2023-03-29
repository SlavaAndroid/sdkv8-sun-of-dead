package jusc.intern.dtfloat.sdkv8.response

sealed class ValidatorRespV8 {
    data class Error(val exception: Exception) : ValidatorRespV8()
    data class Success(val data: RespData) : ValidatorRespV8()
    data class Repeat(val data: RespData) : ValidatorRespV8()
}
