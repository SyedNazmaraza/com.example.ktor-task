package com.example.utils

import com.example.entity.SchemeTable
import com.example.model.SchemeModel
import org.jetbrains.exposed.sql.ResultRow

fun ResultRow.toSchema():SchemeModel{
    return SchemeModel(
        schemeCode = this[SchemeTable.schemeCode],
        schemeName = this[SchemeTable.schemeName]
    )
}