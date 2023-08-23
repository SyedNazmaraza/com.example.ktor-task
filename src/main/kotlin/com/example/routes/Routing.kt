package com.example.routes

import com.example.model.SchemeRequest
import com.example.service.FilterSchemeWithId
import com.example.service.GetAllSchemes
import com.example.service.SearchScheme
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.schemaRoute(){
    val getAllSchemas : GetAllSchemes by inject()
    val searchSchema:SearchScheme by inject()
    val filterSchema:FilterSchemeWithId by inject()

    route("/scheme") {
        get {
            call.respond(getAllSchemas())
        }
        post("/search") {
            val request = call.receive<SchemeRequest>()
            call.respond(searchSchema(request))
        }
        post("/filter") {
            val request = call.receive<SchemeRequest>()
            call.respond(filterSchema(request))
        }
    }
}
