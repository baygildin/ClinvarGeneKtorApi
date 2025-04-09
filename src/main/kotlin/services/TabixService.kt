package com.sbaygildin.ktor.services

import com.sbaygildin.ktor.models.AnnotationResult
import htsjdk.tribble.readers.TabixReader

fun queryTabixAsObject(
    filePath: String,
    rac: String,
    lap: Int,
    rap: Int,
    refkey: String
): AnnotationResult?{
    val reader = TabixReader(filePath)
    val region = "$rac:$lap-$rap"
    val iter = reader.query(region) ?: return null

    var line: String?
    while(iter.next().also { line = it } != null){
        val fields = line!!.split("\t")
        if (fields.getOrNull(3) == refkey) {
            reader.close()
            return AnnotationResult(
                rac = fields[0],
                lap = fields[1].toInt(),
                rap = fields[2].toInt(),
                refkey = fields[3],
                id = fields[4],
                significance = fields[5],
                criteria = fields[6],
                variantType = fields[7]
            )
        }
    }
    reader.close()
    return null
}
