package com.example.plugins

import com.example.repository.SchemeRepository
import com.example.repository.SchemeRepositoryImpl
import com.example.service.GetAllSchemes
import com.example.service.SearchScheme
import org.koin.dsl.module
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configKoin(){
    val module = module {
        single <SchemeRepository>{SchemeRepositoryImpl()}
        single { GetAllSchemes(get()) }
        single { SearchScheme(get()) }
    }
    install(Koin){
        modules(module)
    }
}