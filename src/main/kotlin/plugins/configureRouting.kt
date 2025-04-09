package com.sbaygildin.ktor.plugins



import com.sbaygildin.ktor.routing.infoRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        infoRoute()
    }
}
