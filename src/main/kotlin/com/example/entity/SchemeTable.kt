package com.example.entity

import org.jetbrains.exposed.sql.Table

object SchemeTable: Table(name = "scheme_table"){
    val schemeCode = varchar(name = "scheme_code", length = 256)
    val schemeName = varchar(name = "scheme_name", length = 256)
}
