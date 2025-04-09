package com.sbaygildin.ktor.models

import kotlinx.serialization.Serializable

@Serializable
data class AnnotationResult(
    val rac: String,
    val lap: Int,
    val rap: Int,
    val refkey: String,
    val id: String,
    val significance: String,
    val criteria: String,
    val variantType: String
)