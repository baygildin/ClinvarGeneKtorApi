package com.sbaygildin.ktor

import com.sbaygildin.ktor.routing.infoRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        infoRoute()
    }
}

