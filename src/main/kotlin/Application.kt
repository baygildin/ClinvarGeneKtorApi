package com.sbaygildin.ktor

import com.sbaygildin.ktor.plugins.configureHTTP
import com.sbaygildin.ktor.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.sbaygildin.ktor.routing.infoRoute
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)


}

fun Application.module() {
    println("[DEBUG] Config: ${environment.config}")

    configureSerialization()
    configureHTTP()

    configureRouting()
}

