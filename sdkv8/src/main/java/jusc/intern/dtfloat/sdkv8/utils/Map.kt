package jusc.intern.dtfloat.sdkv8.utils

import jusc.intern.dtfloat.sdkv8.api.model.InputData
import jusc.intern.dtfloat.sdkv8.response.RespData

private const val DEF_VALUE = "null"

fun mapInputDataToRespData(inputData: InputData): RespData {

    return RespData(
        adEvId = inputData.adEvId ?: DEF_VALUE,
        adGrId = inputData.adGrpId ?: DEF_VALUE,
        adGrName = inputData.adGrpNm ?: DEF_VALUE,
        adType = inputData.adTp ?: DEF_VALUE,
        attState = inputData.stateAtt ?: DEF_VALUE,
        cmpgID = inputData.cmpgId ?: DEF_VALUE,
        cmpName = inputData.cmpName ?: DEF_VALUE,
        errs = inputData.err ?: DEF_VALUE,
        idExt = inputData.extId ?: DEF_VALUE,
        ntwSubtype = inputData.ntwrSubtp ?: DEF_VALUE,
        ntwType = inputData.ntwrTp ?: DEF_VALUE,
        from = inputData.src ?: DEF_VALUE,
        orCost = inputData.orgCst ?: DEF_VALUE
    )
}