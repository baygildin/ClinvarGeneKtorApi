package com.sbaygildin.ktor.routing

import com.sbaygildin.ktor.services.queryTabixAsObject
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File
fun Route.infoRoute() {
    get("/info") {
        println(">> Запрос получен: /info")

        try {
            val filePath = System.getenv("FILE_PATH")
                ?: error("Environment variable FILE_PATH is not set")

            println(">> FILE_PATH=$filePath")

            val rac = call.parameters["rac"]
            val lap = call.parameters["lap"]?.toIntOrNull()
            val rap = call.parameters["rap"]?.toIntOrNull()
            val refkey = call.parameters["refkey"]

            println(">> Параметры: rac=$rac, lap=$lap, rap=$rap, refkey=$refkey")

            if (rac == null || lap == null || rap == null || refkey == null) {
                call.respond(HttpStatusCode.BadRequest, "Missing or invalid parameters")
                return@get
            }

            val result = queryTabixAsObject(
                filePath = filePath,
                rac = rac,
                lap = lap,
                rap = rap,
                refkey = refkey
            )

            if (result == null) {
                call.respond(HttpStatusCode.NotFound, "Variant not found")
            } else {
                call.respond(result)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.InternalServerError, "Internal error: ${e.message}")
        }
    }
}

//
//fun Route.infoRoute() {
//    get("/info") {
////        val filePath = environment.config.property("ktor.custom.filePath").getString()
////        val filePath = application.environment.config.property("ktor.custom.filePath").getString()
////        val filePath = "C:\\Users\\S\\Desktop\\archive_stem\\smallstem\\test_data.tsv.gz"
//
//        val filePath = System.getenv("FILE_PATH") ?: error("Environment variable FILE_PATH is not set")
//        val file = File(filePath)
//        println("[DEBUG] FILE_PATH = $filePath")
//        println("[DEBUG] File exists: ${file.exists()}")
//        println("[DEBUG] File size: ${file.length()}")
//        val rac = call.parameters["rac"]
//        val lap = call.parameters["lap"]?.toIntOrNull()
//        val rap = call.parameters["rap"]?.toIntOrNull()
//        val refkey = call.parameters["refkey"]
//
//        if (rac == null || lap == null || rap == null || refkey == null) {
//            call.respond(HttpStatusCode.BadRequest, "Missing or invalid parameters")
//            return@get
//        }
//        val result = queryTabixAsObject(
//            filePath = filePath,
//            rac = rac,
//            lap = lap,
//            rap = rap,
//            refkey = refkey
//        )
//        if (result == null) {
//            call.respond(HttpStatusCode.NotFound, "Variant not found")
//        } else {
//            call.respond(result)
//        }
//    }
//
//}
