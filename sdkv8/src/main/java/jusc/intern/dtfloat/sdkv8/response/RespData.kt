package jusc.intern.dtfloat.sdkv8.response

private const val DEF_VALUE = "null"

@kotlinx.serialization.Serializable
data class RespData(
    val adEvId: String = DEF_VALUE,
    val adGrId: String = DEF_VALUE,
    val adGrName: String = DEF_VALUE,
    val adType: String = DEF_VALUE,
    val attState: String = DEF_VALUE,
    val cmpgID: String = DEF_VALUE,
    val cmpName: String = DEF_VALUE,
    val errs: String = DEF_VALUE,
    val idExt: String = DEF_VALUE,
    val ntwSubtype: String = DEF_VALUE,
    val ntwType: String = DEF_VALUE,
    val from: String = DEF_VALUE,
    val orCost: String = DEF_VALUE
)