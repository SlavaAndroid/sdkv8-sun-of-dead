package jusc.intern.dtfloat.sdkv8.installer

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import jusc.intern.dtfloat.sdkv8.RefException
import jusc.intern.dtfloat.sdkv8.api.model.AppInfoData
import jusc.intern.dtfloat.sdkv8.api.model.RefData
import jusc.intern.dtfloat.sdkv8.api.model.UserAgent
import jusc.intern.dtfloat.sdkv8.repository.remote.RemoteRepo
import jusc.intern.dtfloat.sdkv8.response.RespData
import jusc.intern.dtfloat.sdkv8.response.ValidatorRespV8
import jusc.intern.dtfloat.sdkv8.utils.mapInputDataToRespData
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainJob(
    private val context: Context,
    private val gadId: String,
    private val remoteRepo: RemoteRepo
) {

    suspend fun requestRemote(): ValidatorRespV8 {
        val referrerClient = InstallReferrerClient.newBuilder(context).build()

        val responseCode = suspendCoroutine { con ->
            referrerClient.startConnection(object : InstallReferrerStateListener {
                override fun onInstallReferrerSetupFinished(responseCode: Int) {
                   con.resume(responseCode)
                }

                override fun onInstallReferrerServiceDisconnected() {
                    con.resume(InstallReferrerClient.InstallReferrerResponse.SERVICE_DISCONNECTED)
                }
            })
        }
        return responseProcessing(referrerClient, responseCode)
    }

    private suspend fun responseProcessing(referrerClient: InstallReferrerClient, responseCode: Int): ValidatorRespV8 {
        return when(responseCode) {
            InstallReferrerClient.InstallReferrerResponse.OK -> {
                val info = referrerClient.installReferrer
                val url = info.installReferrer

                referrerClient.endConnection()
                sendRequestRef(url)
            }
            InstallReferrerClient.InstallReferrerResponse.FEATURE_NOT_SUPPORTED -> {
                referrerClient.endConnection()
                sendRequestOrganic()
            }
            InstallReferrerClient.InstallReferrerResponse.SERVICE_UNAVAILABLE -> {
                referrerClient.endConnection()
                sendRequestOrganic()
            }
            else -> {ValidatorRespV8.Error(RefException(responseCode))}
        }
    }

    private suspend fun sendRequestRef(
        url: String
    ): ValidatorRespV8 {

        if (url.contains("fb4a")) {
            val refData = RefData(
                dfa3523 = url,
                ue25123d = 0,
                wr215dsa = 0,
                a31235gasd = gadId,
                sd1253 = context.packageName
            )
            val inputData = remoteRepo.sendRefData(refData).data

            return if (inputData != null) {
                ValidatorRespV8.Success(mapInputDataToRespData(inputData))
            } else {
                ValidatorRespV8.Error(java.lang.Exception("Input data is null!"))
            }

        } else if (url.contains("names=")) {
            val refData = RefData(
                dfa3523 = url.substringAfter("names="),
                ue25123d = 0,
                wr215dsa = 0,
                a31235gasd = gadId,
                sd1253 = context.packageName
            )
            remoteRepo.sendRefData(refData)
            return ValidatorRespV8.Success(RespData(cmpName = url.substringAfter("names=")))

        } else {
            return sendRequestOrganic()
        }
    }

    private suspend fun sendRequestOrganic(): ValidatorRespV8 {
        val userAgent = "Android ${Build.VERSION.RELEASE}; " +
                "${Locale.getDefault()}; " +
                "${Build.MODEL}; " +
                "Build/${Build.ID}"

        val appInfoData = AppInfoData(
            sdkVersion = "1.0",
            limitAdTracking = 0,
            bundle = context.packageName,
            gadId = gadId,
            appVersion = versionCode(),
            osVersion = Build.VERSION.RELEASE,
            idType = 0,
            timestamp = System.currentTimeMillis() / 1000f,
            data = UserAgent(cmf5f15 = userAgent)
        )
        val inputData = remoteRepo.sendAppInfo(appInfoData).data

        return if (inputData != null) {
            ValidatorRespV8.Success(mapInputDataToRespData(inputData))
        } else {
            ValidatorRespV8.Error(java.lang.Exception("Input data is null!"))
        }
    }

    private fun versionCode(): Long = try {
        val info = packageInfo()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            info.longVersionCode
        } else {
            info.versionCode.toLong()
        }
    } catch (ex: Exception) {
        -1
    }

    private fun packageInfo(): PackageInfo {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getPackageInfo(context.packageName, PackageManager.PackageInfoFlags.of(0))
        } else {
            context.packageManager.getPackageInfo(context.packageName, 0)
        }
    }
}