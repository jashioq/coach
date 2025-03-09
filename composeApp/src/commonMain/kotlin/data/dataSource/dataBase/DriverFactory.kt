package data.dataSource.dataBase

import app.cash.sqldelight.db.SqlDriver
import org.jh.coach.data.local.database.Database

const val DB_NAME = "sqldelight.db"

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): Database {
    return Database(driverFactory.createDriver())
}
