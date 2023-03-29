package jusc.intern.dtfloat.sdkv8.repository.remote

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(errorMessage: String) : Resource<T>(message = errorMessage)
}