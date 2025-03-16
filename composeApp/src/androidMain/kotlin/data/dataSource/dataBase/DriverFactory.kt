package data.dataSource.dataBase

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.jh.coach.data.local.database.Database

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(Database.Schema, AppContextWrapper.appContext!!, DB_NAME)
    }
}
