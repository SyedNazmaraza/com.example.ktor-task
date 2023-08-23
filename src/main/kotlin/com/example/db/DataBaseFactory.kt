package com.example.db

import com.example.entity.SchemeTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DataBaseFactory {
    fun init() {
        val db = Database.connect(hikari())
        transaction(db) {
            SchemaUtils.create(SchemeTable)
        }
    }
    private fun hikari() : HikariDataSource{
        val config = HikariConfig()
        config.jdbcUrl = "jdbc:postgresql://localhost:5432/"
        config.driverClassName = "org.postgresql.Driver"
        config.username = "postgres"
        config.password = "root"
        config.maximumPoolSize = 3
        config.isAutoCommit =  false
        config.transactionIsolation= "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}
suspend fun <T> dbQuery(block : suspend () -> T):T =
    newSuspendedTransaction(Dispatchers.IO) {  block()}