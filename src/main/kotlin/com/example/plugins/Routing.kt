package com.example.plugins

import com.example.routes.schemaRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configRouting(){
    routing {
        schemaRoute()
    }
}